package com.enterprise.ai.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.ConversationMessage;

@Component
public class ConversationMemoryRowMapper
        implements RowMapper<ConversationMessage> {

    @Override
    public ConversationMessage mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        ConversationMessage message =
                new ConversationMessage();

        message.setId(
                rs.getLong("id"));

        message.setSessionId(
                rs.getString("session_id"));

        message.setConversationId(
                rs.getString("conversation_id"));

        message.setMessageRole(
                rs.getString("message_role"));

        message.setMessage(
                rs.getString("message"));

        message.setMessageType(
                rs.getString("message_type"));

        message.setToolName(
                rs.getString("tool_name"));

        message.setAgentName(
                rs.getString("agent_name"));

        message.setMetadata(
                rs.getString("metadata"));

        int tokenCount =
                rs.getInt("token_count");

        if (!rs.wasNull()) {
            message.setTokenCount(tokenCount);
        }

        long responseTime =
                rs.getLong("response_time_ms");

        if (!rs.wasNull()) {
            message.setResponseTimeMs(responseTime);
        }

        if (rs.getTimestamp("created_at") != null) {
            message.setCreatedAt(
                    rs.getTimestamp("created_at")
                            .toLocalDateTime());
        }

        return message;
    }

}