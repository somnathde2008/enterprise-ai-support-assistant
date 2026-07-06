package com.enterprise.ai.repository;

import java.util.List;

import com.enterprise.ai.model.ConversationMessage;

public interface ConversationMemoryRepository {

    void save(ConversationMessage message);

    List<ConversationMessage> findByConversationId(
            String conversationId,
            int limit);

}