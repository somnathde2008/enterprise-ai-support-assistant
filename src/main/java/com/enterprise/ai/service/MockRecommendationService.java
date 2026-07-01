package com.enterprise.ai.service;

import org.springframework.stereotype.Service;

import com.enterprise.ai.model.Recommendation;

@Service
public class MockRecommendationService {

    public Recommendation getRecommendation(

            String category,

            int confidence,

            String rootCause,

            boolean escalationRequired) {

        Recommendation recommendation = new Recommendation();

        recommendation.setCategory(category);

        recommendation.setEscalationRequired(escalationRequired);

        recommendation.setRecommendedKB("General KB");

        recommendation.setRecommendedTeam("TPMS Support");

        recommendation.setEstimatedResolutionTime("60 Minutes");

        recommendation.setPreventiveAction(
                "Monitor application health.");

        String root = rootCause.toLowerCase();

        switch (category.toLowerCase()) {

        case "application":

            recommendation.setRecommendedTeam(
                    "Application Support");

            recommendation.setRecommendedKB("KB1003");

            recommendation.setPreventiveAction(
                    "Enable JVM monitoring and Heap Dump.");

            break;

        case "database":

            recommendation.setRecommendedTeam(
                    "Database Team");

            recommendation.setRecommendedKB("KB1001");

            recommendation.setPreventiveAction(
                    "Monitor Azure SQL connectivity.");

            break;

        case "integration":

            recommendation.setRecommendedTeam(
                    "Middleware Team");

            recommendation.setRecommendedKB("KB1002");

            recommendation.setPreventiveAction(
                    "Monitor Kafka Consumer Lag.");

            break;

        case "network":

            recommendation.setRecommendedTeam(
                    "Network Team");

            recommendation.setRecommendedKB("KB2001");

            break;

        case "security":

            recommendation.setRecommendedTeam(
                    "Security Team");

            recommendation.setRecommendedKB("KB3001");

            break;
        }

        // Root Cause Rules

        if (root.contains("heap")
                || root.contains("memory")) {

            recommendation.setRecommendedKB("KB1003");

            recommendation.setPreventiveAction(
                    "Increase JVM Heap and monitor Memory.");

        }

        if (root.contains("kafka")) {

            recommendation.setRecommendedKB("KB1002");

        }

        if (root.contains("sql")
                || root.contains("database")) {

            recommendation.setRecommendedKB("KB1001");

        }

        // Confidence Rules

        if (confidence >= 90) {

            recommendation.setEstimatedResolutionTime(
                    "20 Minutes");

        }

        else if (confidence >= 75) {

            recommendation.setEstimatedResolutionTime(
                    "30 Minutes");

        }

        else {

            recommendation.setEstimatedResolutionTime(
                    "60 Minutes");

        }

        // Escalation Rules

        if (escalationRequired) {

            recommendation.setEstimatedResolutionTime(
                    "Immediate");

        }

        return recommendation;

    }

}