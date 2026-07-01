package com.enterprise.ai.tool;

import java.util.Collection;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.Incident;
import com.enterprise.ai.repository.IncidentRepository;

@Component
public class IncidentTool {

    private final IncidentRepository repository;

    public IncidentTool(IncidentRepository repository) {
        this.repository = repository;
    }

    @Tool(description = "Get incident details using incident number")
    public Incident getIncident(String incidentNumber) {

        return repository.findByIncidentNumber(incidentNumber);

    }

    @Tool(description = """
            Analyze a TPMS production incident.

            Return:
            1. Incident Category
            2. Root Cause
            3. Confidence Score
            4. Resolution Steps
            5. Escalation Required
            """)
    public String analyzeIncident(String incidentNumber) {

        Incident incident =
                repository.findByIncidentNumber(incidentNumber);

        if (incident == null) {
            return "Incident not found";
        }

        return """
                TPMS Production Incident

                Incident Number: %s
                Short Description: %s
                Description: %s
                Priority: %s
                Assignment Group: %s
                Status: %s

                Analyze this incident using ONLY the information above.

                Allowed Categories:

                Connectivity
                Application
                Database
                Performance
                Infrastructure
                Deployment
                Configuration
                Security
                Integration
                Storage
                Network
                Data

                Rules:

                - Do not invent information.
                - Use only the incident details provided.
                - If information is insufficient,
                  provide the most likely root cause.
                - Confidence must be between 0 and 100.
                - Resolution must contain at least 3 troubleshooting steps.
                - EscalationRequired must be true or false.
                - Return ONLY valid JSON.
                - Do NOT explain.
                - Do NOT return markdown.

                {
                  "category":"",
                  "confidence":0,
                  "rootCause":"",
                  "resolution":[
                    "",
                    "",
                    ""
                  ],
                  "escalationRequired":false
                }
                """
                .formatted(
                        incident.getIncidentNumber(),
                        incident.getShortDescription(),
                        incident.getDescription(),
                        incident.getPriority(),
                        incident.getAssignmentGroup(),
                        incident.getStatus());
    }

    @Tool(description = "Get all incidents")
    public Collection<Incident> getAllIncidents() {

        return repository.findAll();

    }

    @Tool(description = "Create new incident")
    public Incident createIncident(Incident incident) {

        return repository.save(incident);

    }

    @Tool(description = "Update incident status")
    public String updateIncidentStatus(
            String incidentNumber,
            String status) {

        boolean updated =
                repository.updateStatus(
                        incidentNumber,
                        status);

        if (updated) {
            return "Incident updated successfully.";
        }

        return "Incident not found.";
    }

    @Tool(description = "Search incidents by keyword")
    public Collection<Incident> searchIncidents(
            String keyword) {

        return repository.searchByKeyword(keyword);

    }

}