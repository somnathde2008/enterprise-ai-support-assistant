package com.enterprise.ai.service;

import org.springframework.stereotype.Service;

import com.enterprise.ai.model.Recommendation;

@Service
public class RecommendationService {

    public Recommendation getRecommendation(
            String category,
            int confidence,
            String rootCause,
            boolean escalationRequired) {

        Recommendation recommendation = new Recommendation();

        recommendation.setRecommendedTeam("Platform Team");
        recommendation.setRecommendedKB("No KB Found");
        recommendation.setEstimatedResolutionTime("30 Minutes");
        recommendation.setPreventiveAction("Investigate root cause");
        recommendation.setEscalationRequired(escalationRequired);

        return recommendation;
    }
}