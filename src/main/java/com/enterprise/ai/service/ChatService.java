package com.enterprise.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enterprise.ai.orchestrator.AIOrchestrator;
import com.enterprise.ai.orchestrator.AnalysisResponse;

@Service
public class ChatService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChatService.class);

    private final ChatClient agentChatClient;

    private final AIOrchestrator orchestrator;

    public ChatService(
            @Qualifier("agentChatClient")
            ChatClient agentChatClient,
            AIOrchestrator orchestrator) {

        this.agentChatClient = agentChatClient;
        this.orchestrator = orchestrator;
    }

    /**
     * Handles normal AI chat requests.
     *
     * @param message User message
     * @return AI response
     */
    public String chat(String message) {

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException(
                    "Message cannot be null or empty.");
        }

        LOGGER.info("Received chat request.");

        long startTime = System.currentTimeMillis();

        try {

            String response =
                    agentChatClient.prompt()
                            .user(message)
                            .call()
                            .content();

            LOGGER.info(
                    "Chat request completed successfully in {} ms.",
                    System.currentTimeMillis() - startTime);

            return response;

        } catch (Exception ex) {

            LOGGER.error(
                    "Error while processing AI chat request.",
                    ex);

            throw new RuntimeException(
                    "Unable to process AI request.",
                    ex);

        }

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