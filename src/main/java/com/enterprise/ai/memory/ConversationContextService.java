package com.enterprise.ai.memory;

public interface ConversationContextService {

    /**
     * Build AI context from previous conversation.
     *
     * @param sessionId Current session
     * @param currentMessage Current user message
     * @return Final prompt for AI
     */
    String buildContext(
            String sessionId,
            String currentMessage);

}