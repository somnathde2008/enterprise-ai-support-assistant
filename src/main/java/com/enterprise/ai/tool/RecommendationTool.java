package com.enterprise.ai.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.Recommendation;
import com.enterprise.ai.service.RecommendationService;

@Component
public class RecommendationTool {

    private final RecommendationService service;

    public RecommendationTool(
            RecommendationService service) {

        this.service = service;

    }

    @Tool(description = """
    		Provide recommendation after incident analysis.

    		Input:
    		Incident Category

    		Return

    		Recommended Team

    		Recommended KB

    		Estimated Resolution Time

    		Preventive Action

    		Escalation Required
    		""")
    public Recommendation getRecommendation(
            String category,
            int confidence,
            String rootCause,
            boolean escalationRequired) {

        return service.getRecommendation(
                category,
                confidence,
                rootCause,
                escalationRequired);
    }

}