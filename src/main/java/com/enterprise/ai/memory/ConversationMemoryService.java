package com.enterprise.ai.memory;

import java.util.List;

import com.enterprise.ai.model.ConversationMessage;

/**
 * Service responsible for managing AI conversation memory.
 *
 * Implementations may use:
 * - SQL Server
 * - MySQL
 * - PostgreSQL
 * - Redis
 * - Azure Cosmos DB
 * - MCP Memory
 */
public interface ConversationMemoryService {

    /**
     * Save a conversation message.
     *
     * @param message Conversation message
     */
    void saveMessage(
            ConversationMessage message);

    /**
     * Load recent conversation history.
     *
     * @param sessionId Current session id
     * @param limit Number of messages
     * @return Conversation history
     */
    List<ConversationMessage> loadConversation(
            String sessionId,
            int limit);

    /**
     * Delete conversation history.
     *
     * @param sessionId Session id
     */
    void deleteConversation(
            String sessionId);

    /**
     * Check whether a conversation exists.
     *
     * @param sessionId Session id
     * @return true if conversation exists
     */
    boolean exists(
            String sessionId);
    
    
    /**
     * Save user message.
     *
     * @param sessionId Session identifier
     * @param message User message
     */
    void saveUserMessage(
            String sessionId,
            String message);

    /**
     * Save assistant message.
     *
     * @param sessionId Session identifier
     * @param message AI response
     */
    void saveAssistantMessage(
            String sessionId,
            String message);

}