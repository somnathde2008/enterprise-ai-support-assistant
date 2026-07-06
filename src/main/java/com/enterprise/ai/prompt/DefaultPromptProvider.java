package com.enterprise.ai.prompt;

import org.springframework.stereotype.Component;

@Component
public class DefaultPromptProvider
        implements PromptProvider {

    @Override
    public String getPrompt(
            PromptType type) {

        return switch (type) {

            case AGENT ->
                    AgentSystemPrompt.SYSTEM_PROMPT;

            case INCIDENT_ANALYSIS ->
                    IncidentAnalysisPrompt.SYSTEM_PROMPT;

            case INCIDENT_CLASSIFICATION ->
                    IncidentClassificationPrompt.SYSTEM_PROMPT;

            case KNOWLEDGE ->
                    KnowledgePrompt.SYSTEM_PROMPT;

            case RECOMMENDATION ->
                    RecommendationPrompt.SYSTEM_PROMPT;

            case JSON_ANALYSIS ->
                    JsonAnalysisPrompt.SYSTEM_PROMPT;
        };
    }

}