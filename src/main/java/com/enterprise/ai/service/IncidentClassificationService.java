package com.enterprise.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.enterprise.ai.dto.IncidentClassificationResponse;
import com.enterprise.ai.prompt.PromptTemplate;

@Service
public class IncidentClassificationService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(IncidentClassificationService.class);

    private final ChatClient chatClient;

    public IncidentClassificationService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * AI based Incident Classification.
     *
     * Predicts:
     * - Category
     * - Priority
     * - Assignment Group
     * - Confidence
     * - Reason
     *
     * @param shortDescription Incident Short Description
     * @param description Incident Description
     * @return IncidentClassificationResponse
     */
    public IncidentClassificationResponse classifyIncident(
            String shortDescription,
            String description) {

        LOGGER.info("AI Incident Classification started.");

        String prompt =
                PromptTemplate
                        .buildIncidentClassificationPrompt(
                                shortDescription,
                                description);

        try {

            IncidentClassificationResponse response =
                    chatClient.prompt()
                            .user(prompt)
                            .call()
                            .entity(IncidentClassificationResponse.class);

            LOGGER.info(
                    "Incident classified successfully. Category={}, Priority={}, AssignmentGroup={}, Confidence={}",
                    response.getCategory(),
                    response.getPriority(),
                    response.getAssignmentGroup(),
                    response.getConfidence());

            return response;

        } catch (Exception ex) {

            LOGGER.error(
                    "AI Incident Classification failed.",
                    ex);

            throw new RuntimeException(
                    "Unable to classify incident.",
                    ex);

        }

    }

}