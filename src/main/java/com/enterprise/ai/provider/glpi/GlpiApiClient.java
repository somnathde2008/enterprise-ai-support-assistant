package com.enterprise.ai.provider.glpi;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.enterprise.ai.config.GlpiProperties;
import com.enterprise.ai.dto.glpi.CreateTicketRequest;
import com.enterprise.ai.dto.glpi.TicketResponse;

@Component
public class GlpiApiClient {

    private final WebClient webClient;
    private final GlpiProperties glpiProperties;

    public GlpiApiClient(WebClient webClient,
                             GlpiProperties glpiProperties) {
        this.webClient = webClient;
        this.glpiProperties = glpiProperties;
    }
    
    @SuppressWarnings("unchecked")
    public String createSession() {

        try {

            Map<String, Object> response =
                    webClient.get()
                            .uri(glpiProperties.getBaseUrl() + GlpiEndpoints.INIT_SESSION)
                            .header(GlpiConstants.APP_TOKEN, glpiProperties.getAppToken())
                            .header(GlpiConstants.AUTHORIZATION, GlpiConstants.USER_TOKEN_PREFIX + glpiProperties.getUserToken())
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(Map.class)
                            .block();

            if (response == null || !response.containsKey("session_token")) {
                throw new RuntimeException("Unable to create GLPI session.");
            }

            return response.get("session_token").toString();

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Failed to create GLPI session: " + ex.getMessage(),
                    ex);

        }

    }
    
    
    
    /**
     * Create common GLPI request headers.
     */
    public HttpHeaders createHeaders(String sessionToken) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(
                java.util.List.of(MediaType.APPLICATION_JSON));

        headers.set(
                GlpiConstants.APP_TOKEN,
                glpiProperties.getAppToken());

        headers.set(
                GlpiConstants.SESSION_TOKEN,
                sessionToken);

        return headers;
    }
    
    

    public void closeSession(String sessionToken) {

        try {

            webClient.get()
            		.uri(glpiProperties.getBaseUrl() + GlpiEndpoints.KILL_SESSION)
            		.header(GlpiConstants.APP_TOKEN, glpiProperties.getAppToken())
            		.header(GlpiConstants.SESSION_TOKEN, sessionToken)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

        } catch (Exception ex) {

            // Ignore close session errors

        }

    }
    
       
    
    
    public TicketResponse createTicket(CreateTicketRequest request) {

        String sessionToken = createSession();

        try {

            HttpHeaders headers = createHeaders(sessionToken);

            return webClient.post()
                    .uri(glpiProperties.getBaseUrl()
                            + GlpiEndpoints.TICKET)
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(TicketResponse.class)
                    .block();

        } finally {

            closeSession(sessionToken);

        }

    }
    
    public TicketResponse getTicket(Long ticketId) {

        String sessionToken = createSession();

        try {

            HttpHeaders headers = createHeaders(sessionToken);

            return webClient.get()
                    .uri(glpiProperties.getBaseUrl()
                            + GlpiEndpoints.TICKET
                            + "/"
                            + ticketId)
                    .headers(h -> h.addAll(headers))
                    .retrieve()
                    .bodyToMono(TicketResponse.class)
                    .block();

        } finally {

            closeSession(sessionToken);

        }

    }
    
    
    public Map<String, Object> searchTickets(String keyword) {

        String sessionToken = createSession();

        try {

            HttpHeaders headers = createHeaders(sessionToken);

            Map<String, Object> result= webClient.get()
            		.uri(glpiProperties.getBaseUrl()
            		        + "/search/Ticket"
            		        + "?forcedisplay[0]=1"
            		        + "&forcedisplay[1]=2"
            		        + "&forcedisplay[2]=3"
            		        + "&forcedisplay[3]=12"
            		        + "&criteria[0][field]=1"
            		        + "&criteria[0][searchtype]=contains"
            		        + "&criteria[0][value]=" + keyword)
                    .headers(h -> h.addAll(headers))
                    .retrieve()
                    .bodyToMono(
                            new ParameterizedTypeReference<Map<String, Object>>() {
                            })
                    .block();
            
            System.out.println("========== GLPI SEARCH RESPONSE ==========");
            System.out.println(result);
            
            return result;
            

        } finally {

            closeSession(sessionToken);

        }

    }
    
    
    public boolean updateTicket(
            Long ticketId,
            Map<String, Object> input) {

        String sessionToken = createSession();

        try {

            HttpHeaders headers = createHeaders(sessionToken);

            webClient.put()
                    .uri(glpiProperties.getBaseUrl()
                            + GlpiEndpoints.TICKET
                            + "/"
                            + ticketId)
                    .headers(h -> h.addAll(headers))
                    .bodyValue(Map.of("input", input))
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            closeSession(sessionToken);

        }

    }
    
    
    
    public boolean deleteTicket(Long ticketId) {

        String sessionToken = createSession();

        try {

            HttpHeaders headers = createHeaders(sessionToken);

            webClient.delete()
                    .uri(glpiProperties.getBaseUrl()
                            + GlpiEndpoints.TICKET
                            + "/"
                            + ticketId)
                    .headers(h -> h.addAll(headers))
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            closeSession(sessionToken);

        }

    }
    
    
    
    @SuppressWarnings("unchecked")
    public Map<String, Object> searchKnowledge(
            String keyword) {

        String sessionToken = createSession();

        try {

            HttpHeaders headers =
                    createHeaders(sessionToken);

            Map<String, Object> response= webClient.get()
                    .uri(glpiProperties.getBaseUrl()
                            + "/search/KnowbaseItem"
                            + "?criteria[0][field]=1"
                            + "&criteria[0][searchtype]=contains"
                            + "&criteria[0][value]="
                            + keyword)
                    .headers(h -> h.addAll(headers))
                    .retrieve()
                    .bodyToMono(
                            new ParameterizedTypeReference<Map<String,Object>>() {})
                    .block();
            
            System.out.println("========== GLPI KNOWLEDGE SEARCH ==========");
            System.out.println(response);
            
            return response;

        } finally {

            closeSession(sessionToken);

        }

    }
    
    
    
    @SuppressWarnings("unchecked")
    public Map<String, Object> getKnowledge(
            Long id) {

        String sessionToken = createSession();

        try {

            HttpHeaders headers =
                    createHeaders(sessionToken);

            return webClient.get()
                    .uri(glpiProperties.getBaseUrl()
                            + "/KnowbaseItem/"
                            + id)
                    .headers(h -> h.addAll(headers))
                    .retrieve()
                    .bodyToMono(
                            new ParameterizedTypeReference<Map<String,Object>>() {})
                    .block();

        } finally {

            closeSession(sessionToken);

        }

    }

}