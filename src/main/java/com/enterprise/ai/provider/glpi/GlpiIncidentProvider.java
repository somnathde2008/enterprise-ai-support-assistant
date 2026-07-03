package com.enterprise.ai.provider.glpi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.enterprise.ai.dto.glpi.CreateTicketRequest;
import com.enterprise.ai.dto.glpi.TicketResponse;
import com.enterprise.ai.model.Incident;
import com.enterprise.ai.provider.IncidentProvider;

@Component
public class GlpiIncidentProvider implements IncidentProvider {

    private final GlpiTicketClient glpiTicketClient;

    public GlpiIncidentProvider(GlpiTicketClient glpiTicketClient) {
        this.glpiTicketClient = glpiTicketClient;
    }

    @Override
    public String createIncident(
            String shortDescription,
            String description,
            String category,
            String priority) {

        CreateTicketRequest request = new CreateTicketRequest();

        request.getInput().setName(shortDescription);
        request.getInput().setContent(description);
        request.getInput().setItilcategories_id(1);

        request.getInput().setUrgency(mapPriority(priority));
        request.getInput().setImpact(mapPriority(priority));

        TicketResponse response =
                glpiTicketClient.createTicket(request);

        if (response == null || response.getId() == null) {
            throw new RuntimeException("Unable to create GLPI ticket.");
        }

        return "INC-" + response.getId();
    }

    @Override
    public Incident getIncident(String incidentNumber) {

        Long ticketId = extractTicketId(incidentNumber);

        TicketResponse response =
                glpiTicketClient.getTicket(ticketId);

        if (response == null) {
            return null;
        }

        Incident incident = new Incident();

        incident.setid(response.getId());
        incident.setIncidentNumber("INC-" + response.getId());
        incident.setShortDescription(response.getName());
        incident.setDescription(response.getContent());
        incident.setStatus(mapStatus(response.getStatus()));
        incident.setPriority(mapPriorityName(response.getPriority()));
        incident.setCategory(String.valueOf(response.getItilCategoryId()));
        incident.setSourceSystem("GLPI");

        return incident;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Incident> searchIncidents(String keyword) {

        Map<String, Object> response =
                glpiTicketClient.searchTickets(keyword);

        if (response == null || !response.containsKey("data")) {
            return Collections.emptyList();
        }

        List<Map<String, Object>> data =
                (List<Map<String, Object>>) response.get("data");

        List<Incident> incidents = new ArrayList<>();

        for (Map<String, Object> row : data) {

            Incident incident = new Incident();

            Long id = Long.valueOf(row.get("2").toString());

            incident.setid(
                    Long.valueOf(
                            row.get(GlpiSearchFields.ID).toString()));

            incident.setIncidentNumber(
                    "INC-" + row.get(GlpiSearchFields.ID));

            incident.setShortDescription(
                    String.valueOf(
                            row.get(GlpiSearchFields.NAME)));

            incident.setPriority(
                    GlpiMappings.mapPriority(
                            String.valueOf(
                                    row.get(GlpiSearchFields.PRIORITY))));

            incident.setStatus(
                    GlpiMappings.mapStatus(
                            String.valueOf(
                                    row.get(GlpiSearchFields.STATUS))));
            
            incident.setIncidentNumber("INC-" + id);

            
            incident.setSourceSystem("GLPI");

            incidents.add(incident);
        }

        return incidents;
    }

    @Override
    public boolean updateIncidentStatus(
            String incidentNumber,
            String status) {

        throw new UnsupportedOperationException(
                "Not implemented yet.");
    }

    @Override
    public boolean assignIncident(
            String incidentNumber,
            String assignmentGroup,
            String assignedTo) {

        throw new UnsupportedOperationException(
                "Not implemented yet.");
    }

    @Override
    public boolean resolveIncident(
            String incidentNumber,
            String resolution) {

        throw new UnsupportedOperationException(
                "Not implemented yet.");
    }

    /**
     * Priority mapping while creating ticket
     */
    private int mapPriority(String priority) {

        if (priority == null) {
            return 3;
        }

        switch (priority.toUpperCase()) {

            case "P1":
            case "CRITICAL":
                return 5;

            case "P2":
            case "HIGH":
                return 4;

            case "P3":
            case "MEDIUM":
                return 3;

            case "P4":
            case "LOW":
                return 2;

            default:
                return 3;
        }
    }

    /**
     * GLPI Priority -> String
     */
    private String mapPriorityName(Integer priority) {

        if (priority == null) {
            return "UNKNOWN";
        }

        return switch (priority) {
            case 1 -> "VERY LOW";
            case 2 -> "LOW";
            case 3 -> "MEDIUM";
            case 4 -> "HIGH";
            case 5 -> "VERY HIGH";
            case 6 -> "MAJOR";
            default -> "UNKNOWN";
        };
    }

    /**
     * GLPI Status -> String
     */
    private String mapStatus(Integer status) {

        if (status == null) {
            return "UNKNOWN";
        }

        return switch (status) {
            case 1 -> "NEW";
            case 2 -> "ASSIGNED";
            case 3 -> "PLANNED";
            case 4 -> "PENDING";
            case 5 -> "SOLVED";
            case 6 -> "CLOSED";
            default -> "UNKNOWN";
        };
    }

    /**
     * INC-123 -> 123
     */
    private Long extractTicketId(String incidentNumber) {

        return Long.parseLong(
                incidentNumber.replace("INC-", "").trim());
    }
}