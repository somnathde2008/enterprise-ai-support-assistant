package com.enterprise.ai.tool;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.Incident;
import com.enterprise.ai.service.IncidentSimilarityService;

@Component
public class IncidentSimilarityTool {

    private final IncidentSimilarityService incidentSimilarityService;

    public IncidentSimilarityTool(
            IncidentSimilarityService incidentSimilarityService) {

        this.incidentSimilarityService = incidentSimilarityService;
    }

    @Tool(description = """
            Find historical incidents similar to an existing incident.

            Input:
            - Incident Number

            Returns:
            - Top similar incidents
            """)
    public List<Incident> findSimilarIncidents(
            String incidentNumber) {

        return incidentSimilarityService.findSimilarIncidents(
                incidentNumber,
                5);
    }

}