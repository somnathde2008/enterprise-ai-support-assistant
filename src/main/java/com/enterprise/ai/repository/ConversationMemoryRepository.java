package com.enterprise.ai.repository;

import java.util.List;

import com.enterprise.ai.model.ConversationMessage;

public interface ConversationMemoryRepository {

    /**
     * Save a conversation message.
     *
     * @param message Conversation message
     */
    void save(ConversationMessage message);

    /**
     * Retrieve recent conversation history for a session.
     *
     * @param sessionId Session identifier
     * @param limit Maximum number of messages
     * @return Conversation history
     */
    List<ConversationMessage> findBySessionId(
            String sessionId,
            int limit);

    /**
     * Delete all messages for a session.
     *
     * @param sessionId Session identifier
     */
    void deleteBySessionId(
            String sessionId);

    /**
     * Check whether a session has conversation history.
     *
     * @param sessionId Session identifier
     * @return true if conversation exists
     */
    boolean existsBySessionId(
            String sessionId);

}