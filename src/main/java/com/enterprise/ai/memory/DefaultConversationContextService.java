package com.enterprise.ai.memory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enterprise.ai.model.ConversationMessage;

@Service
public class DefaultConversationContextService
        implements ConversationContextService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    DefaultConversationContextService.class);

    private static final int MAX_HISTORY = 10;

    private final ConversationMemoryService memoryService;

    public DefaultConversationContextService(
            ConversationMemoryService memoryService) {

        this.memoryService = memoryService;
    }

    @Override
    public String buildContext(
            String sessionId,
            String currentMessage) {

        LOGGER.info(
                "Building AI context for session {}",
                sessionId);

        List<ConversationMessage> history =
                memoryService.loadConversation(
                        sessionId,
                        MAX_HISTORY);

        StringBuilder prompt =
                new StringBuilder();

        if (!history.isEmpty()) {

            prompt.append("""
Previous Conversation

----------------------------------

""");

            for (ConversationMessage message : history) {

                prompt.append(message.getMessageRole())
                        .append(": ")
                        .append(message.getMessage())
                        .append("\n");

            }

            prompt.append("""

----------------------------------

""");
        }

        prompt.append("Current User Message:\n");

        prompt.append(currentMessage);

        LOGGER.info(
                "Conversation context built successfully.");

        return prompt.toString();
    }

}