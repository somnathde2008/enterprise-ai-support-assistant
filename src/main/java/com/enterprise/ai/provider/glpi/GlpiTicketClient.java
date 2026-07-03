package com.enterprise.ai.provider.glpi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.enterprise.ai.dto.glpi.CreateTicketRequest;
import com.enterprise.ai.dto.glpi.TicketResponse;

@Component
public class GlpiTicketClient {

    private final GlpiApiClient glpiApiClient;

    public GlpiTicketClient(
            GlpiApiClient glpiApiClient) {

        this.glpiApiClient = glpiApiClient;
    }

    /**
     * Create Ticket
     */
    public TicketResponse createTicket(
            CreateTicketRequest request) {

        return glpiApiClient.createTicket(request);

    }

    /**
     * Get Ticket
     */
    public TicketResponse getTicket(
            Long ticketId) {

        return glpiApiClient.getTicket(ticketId);

    }

    /**
     * Search Tickets
     */
    public Map<String, Object> searchTickets(
            String keyword) {

        return glpiApiClient.searchTickets(keyword);

    }

    /**
     * Update Ticket
     */
    public boolean updateTicket(
            Long ticketId,
            Map<String, Object> input) {

        return glpiApiClient.updateTicket(
                ticketId,
                input);

    }

    /**
     * Delete Ticket
     */
    public boolean deleteTicket(
            Long ticketId) {

        return glpiApiClient.deleteTicket(ticketId);

    }

}