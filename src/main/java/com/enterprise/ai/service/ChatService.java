package com.enterprise.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enterprise.ai.memory.ConversationMemoryService;
import com.enterprise.ai.orchestrator.AIOrchestrator;
import com.enterprise.ai.orchestrator.AnalysisResponse;

@Service
public class ChatService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChatService.class);

    private final ChatClient agentChatClient;

    private final AIOrchestrator orchestrator;

    private final ConversationMemoryService conversationMemoryService;

    public ChatService(

            @Qualifier("agentChatClient")
            ChatClient agentChatClient,

            AIOrchestrator orchestrator,

            ConversationMemoryService conversationMemoryService) {

        this.agentChatClient = agentChatClient;
        this.orchestrator = orchestrator;
        this.conversationMemoryService = conversationMemoryService;
    }
    /**
     * Handles normal AI chat requests.
     *
     * @param message User message
     * @return AI response
     */
    public String chat(
            String sessionId,
            String message) {

    	LOGGER.info("Received chat request. SessionId={}", sessionId);

        long start = System.currentTimeMillis();

        conversationMemoryService.saveUserMessage(
                sessionId,
                message);

        String response =
                agentChatClient
                        .prompt()
                        .user(message)
                        .call()
                        .content();

        conversationMemoryService.saveAssistantMessage(
                sessionId,
                response);

        LOGGER.info(
                "Chat completed in {} ms",
                System.currentTimeMillis() - start);

        return response;
    }

    /**
     * Analyze an incident using AI Orchestrator.
     *
     * @param incidentNumber Incident Number
     * @return Analysis Response
     */
    public AnalysisResponse analyzeIncident(
            String incidentNumber) {

        LOGGER.info(
                "Starting incident analysis for Incident={}",
                incidentNumber);

        try {

            AnalysisResponse response =
                    orchestrator.analyzeIncident(
                            incidentNumber);

            LOGGER.info(
                    "Incident analysis completed successfully.");

            return response;

        } catch (Exception ex) {

            LOGGER.error(
                    "Incident analysis failed.",
                    ex);

            throw new RuntimeException(
                    "Unable to analyze incident.",
                    ex);

        }

    }

}