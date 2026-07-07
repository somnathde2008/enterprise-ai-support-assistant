package com.enterprise.ai.memory;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.ai.model.ConversationMessage;
import com.enterprise.ai.repository.ConversationMemoryRepository;

@Service
@Transactional
public class ConversationMemoryServiceImpl
        implements ConversationMemoryService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    ConversationMemoryServiceImpl.class);

    private final ConversationMemoryRepository repository;

    public ConversationMemoryServiceImpl(
            ConversationMemoryRepository repository) {

        this.repository = repository;
    }

    @Override
    public void saveMessage(
            ConversationMessage message) {

        LOGGER.info(
                "Saving conversation message. SessionId={}",
                message.getSessionId());

        if (message.getCreatedAt() == null) {
            message.setCreatedAt(
                    LocalDateTime.now());
        }

        repository.save(message);

        LOGGER.debug(
                "Conversation message saved successfully.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConversationMessage> loadConversation(
            String sessionId,
            int limit) {

        LOGGER.info(
                "Loading conversation history. SessionId={}, Limit={}",
                sessionId,
                limit);

        return repository.findBySessionId(
                sessionId,
                limit);
    }

    @Override
    public void deleteConversation(
            String sessionId) {

        LOGGER.info(
                "Deleting conversation history. SessionId={}",
                sessionId);

        repository.deleteBySessionId(
                sessionId);

        LOGGER.info(
                "Conversation deleted successfully.");
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(
            String sessionId) {

        return repository.existsBySessionId(
                sessionId);
    }
    
    
    @Override
    public void saveUserMessage(
            String sessionId,
            String message) {

        LOGGER.info(
                "Saving USER message. SessionId={}",
                sessionId);

        ConversationMessage conversation =
                new ConversationMessage();

        conversation.setSessionId(sessionId);
        conversation.setMessageRole(MemoryConstants.USER);
        conversation.setMessage(message);
        conversation.setMessageType(MemoryConstants.CHAT);
        conversation.setCreatedAt(LocalDateTime.now());

        repository.save(conversation);

        LOGGER.debug("USER message saved successfully.");
    }

    @Override
    public void saveAssistantMessage(
            String sessionId,
            String message) {

        LOGGER.info(
                "Saving ASSISTANT message. SessionId={}",
                sessionId);

        ConversationMessage conversation =
                new ConversationMessage();

        conversation.setSessionId(sessionId);
        conversation.setMessageRole(MemoryConstants.ASSISTANT);
        conversation.setMessage(message);
        conversation.setMessageType(MemoryConstants.CHAT);
        conversation.setCreatedAt(LocalDateTime.now());

        repository.save(conversation);

        LOGGER.debug("ASSISTANT message saved successfully.");
    }

}