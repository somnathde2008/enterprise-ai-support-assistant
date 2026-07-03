package com.enterprise.ai.service;

import org.springframework.stereotype.Service;

import com.enterprise.ai.model.Incident;

@Service
public class IncidentAnalysisService {

    private final IncidentService incidentService;

    public IncidentAnalysisService(
            IncidentService incidentService) {

        this.incidentService = incidentService;
    }

    public String buildAnalysisPrompt(
            String incidentNumber) {

        Incident incident =
                incidentService.getIncident(
                        incidentNumber);

        if (incident == null) {
            return "Incident not found.";
        }

        return """
You are an Enterprise ITSM Incident Analyst.

Analyze the following incident.

Incident Number:
%s

Short Description:
%s

Description:
%s

Priority:
%s

Status:
%s

Assignment Group:
%s

Category:
%s

Return ONLY valid JSON.

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
incident.getStatus(),
incident.getAssignmentGroup(),
incident.getCategory());

    }

}