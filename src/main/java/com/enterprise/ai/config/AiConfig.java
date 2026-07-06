package com.enterprise.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enterprise.ai.prompt.AgentSystemPrompt;
import com.enterprise.ai.prompt.JsonAnalysisPrompt;
import com.enterprise.ai.tool.IncidentSimilarityTool;
import com.enterprise.ai.tool.IncidentTool;
import com.enterprise.ai.tool.KnowledgeTool;
import com.enterprise.ai.tool.RecommendationTool;

@Configuration
public class AiConfig {

	// ==========================================================
	// Normal Chat Agent
	// ==========================================================

	@Bean("agentChatClient")
	ChatClient agentChatClient(
	        ChatClient.Builder builder,
	        IncidentTool incidentTool,
	        KnowledgeTool knowledgeTool,
	        RecommendationTool recommendationTool,
	        IncidentSimilarityTool incidentSimilarityTool) {

	    return builder
	    		.defaultSystem(
	    		        AgentSystemPrompt.SYSTEM_PROMPT).defaultTools(
	        incidentTool,
	        knowledgeTool,
	        recommendationTool,
	        incidentSimilarityTool)
	            .build();
	}

    // ==========================================================
    // Hybrid JSON Client
    // ==========================================================

    @Bean("jsonChatClient")
    ChatClient jsonChatClient(
            ChatClient.Builder builder,
            IncidentTool incidentTool) {

        return builder
        		.defaultSystem(
        		        JsonAnalysisPrompt.SYSTEM_PROMPT)

                .defaultTools(
                        incidentTool)

                .build();
    }

}