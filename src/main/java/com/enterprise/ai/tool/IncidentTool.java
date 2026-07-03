package com.enterprise.ai.tool;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.Incident;
import com.enterprise.ai.service.IncidentAnalysisService;
import com.enterprise.ai.service.IncidentService;

@Component
public class IncidentTool {

	private final IncidentService incidentService;

	private final IncidentAnalysisService incidentAnalysisService;

	public IncidentTool(
	        IncidentService incidentService,
	        IncidentAnalysisService incidentAnalysisService) {

	    this.incidentService = incidentService;
	    this.incidentAnalysisService = incidentAnalysisService;
	}

    @Tool(description = """
            Create a new IT incident in GLPI.

            Inputs:
            - shortDescription
            - description
            - category
            - priority

            Returns:
            Created Incident Number
            """)
    public String createIncident(
            String shortDescription,
            String description,
            String category,
            String priority) {

        return incidentService.createIncident(
                shortDescription,
                description,
                category,
                priority);
    }

    @Tool(description = "Get incident details using Incident Number")
    public Incident getIncident(
            String incidentNumber) {

        return incidentService.getIncident(
                incidentNumber);
    }

    @Tool(description = """
    		Analyze a TPMS production incident.
    		""")
    		public String analyzeIncident(
    		        String incidentNumber) {

    		    return incidentAnalysisService
    		            .buildAnalysisPrompt(
    		                    incidentNumber);
    		}

    @Tool(description = "Search incidents")
    public List<Incident> searchIncidents(
            String keyword) {

        return incidentService.searchIncidents(
                keyword);
    }

    @Tool(description = "Update incident status")
    public String updateIncidentStatus(
            String incidentNumber,
            String status) {

        boolean updated =
                incidentService.updateIncidentStatus(
                        incidentNumber,
                        status);

        if (updated) {
            return "Incident updated successfully.";
        }

        return "Incident not found.";
    }

    @Tool(description = "Assign incident")
    public String assignIncident(
            String incidentNumber,
            String assignmentGroup,
            String assignedTo) {

        boolean updated =
                incidentService.assignIncident(
                        incidentNumber,
                        assignmentGroup,
                        assignedTo);

        if (updated) {
            return "Incident assigned successfully.";
        }

        return "Incident assignment failed.";
    }

    @Tool(description = "Resolve incident")
    public String resolveIncident(
            String incidentNumber,
            String resolution) {

        boolean updated =
                incidentService.resolveIncident(
                        incidentNumber,
                        resolution);

        if (updated) {
            return "Incident resolved successfully.";
        }

        return "Incident resolution failed.";
    }

}