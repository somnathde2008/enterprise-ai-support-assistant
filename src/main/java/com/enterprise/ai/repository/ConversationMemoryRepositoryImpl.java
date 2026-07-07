package com.enterprise.ai.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enterprise.ai.mapper.ConversationMemoryRowMapper;
import com.enterprise.ai.model.ConversationMessage;

@Repository
public class ConversationMemoryRepositoryImpl
        implements ConversationMemoryRepository {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    ConversationMemoryRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    private final ConversationMemoryRowMapper rowMapper;

    public ConversationMemoryRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            ConversationMemoryRowMapper rowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(
            ConversationMessage message) {

        LOGGER.info(
                "Saving conversation for sessionId={}",
                message.getSessionId());

        String sql = """
                INSERT INTO chat_messages
                (
                    session_id,
                    conversation_id,
                    message_role,
                    message,
                    message_type,
                    tool_name,
                    agent_name,
                    metadata,
                    token_count,
                    response_time_ms,
                    created_at
                )
                VALUES
                (
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                )
                """;

        jdbcTemplate.update(
                sql,
                message.getSessionId(),
                message.getConversationId(),
                message.getMessageRole(),
                message.getMessage(),
                message.getMessageType(),
                message.getToolName(),
                message.getAgentName(),
                message.getMetadata(),
                message.getTokenCount(),
                message.getResponseTimeMs(),
                message.getCreatedAt());

        LOGGER.info("Conversation saved successfully.");
    }

    @Override
    public List<ConversationMessage> findBySessionId(
            String sessionId,
            int limit) {

        LOGGER.info(
                "Loading conversation history for sessionId={}",
                sessionId);

        String sql = """
                SELECT TOP (?)
                    *
                FROM chat_messages
                WHERE session_id = ?
                ORDER BY created_at DESC
                """;

        return jdbcTemplate.query(
                sql,
                rowMapper,
                limit,
                sessionId);
        
		/*
		 * String sql = """ SELECT TOP %d
		 *
		 * FROM chat_messages WHERE session_id = ? ORDER BY created_at DESC
		 * """.formatted(limit);
		 * 
		 * return jdbcTemplate.query( sql, rowMapper, sessionId);
		 */
        
    }

    @Override
    public void deleteBySessionId(
            String sessionId) {

        LOGGER.info(
                "Deleting conversation for sessionId={}",
                sessionId);

        String sql = """
                DELETE
                FROM chat_messages
                WHERE session_id = ?
                """;

        jdbcTemplate.update(
                sql,
                sessionId);

        LOGGER.info("Conversation deleted successfully.");
    }

    @Override
    public boolean existsBySessionId(
            String sessionId) {

        String sql = """
                SELECT COUNT(*)
                FROM chat_messages
                WHERE session_id = ?
                """;

        Integer count =
                jdbcTemplate.queryForObject(
                        sql,
                        Integer.class,
                        sessionId);

        return count != null && count > 0;
    }

}