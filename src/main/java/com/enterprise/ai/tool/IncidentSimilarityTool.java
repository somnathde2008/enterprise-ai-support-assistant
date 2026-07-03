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
            Find incidents similar to the user's problem.

            Use this tool when:
            - User asks for similar incidents
            - User wants historical incidents
            - User asks whether the issue happened before
            - User asks to find previous incidents

            Returns matching incidents.
            """)
    public List<Incident> findSimilarIncidents(
            String keyword) {

        return incidentSimilarityService
                .findSimilarIncidents(keyword);

    }

}