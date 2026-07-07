package com.enterprise.ai.memory;

import java.util.List;

import com.enterprise.ai.model.ConversationMessage;

public interface PromptContextBuilder {

    String buildContext(
            List<ConversationMessage> history,
            String currentMessage);

}