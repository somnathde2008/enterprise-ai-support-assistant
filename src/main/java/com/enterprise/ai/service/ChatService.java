package com.enterprise.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enterprise.ai.orchestrator.AIOrchestrator;
import com.enterprise.ai.orchestrator.HybridResponse;

@Service
public class ChatService {

    private final ChatClient agentChatClient;

    private final AIOrchestrator orchestrator;

    public ChatService(
            @Qualifier("agentChatClient")
            ChatClient agentChatClient,
            AIOrchestrator orchestrator) {

        this.agentChatClient = agentChatClient;
        this.orchestrator = orchestrator;
    }

    // Normal AI Chat
    public String chat(String message) {

        System.out.println("========== CHAT ==========");
        System.out.println("Before Chat");
        System.out.println("Message = " + message);

        long start = System.currentTimeMillis();

        String response = agentChatClient
                .prompt()
                .user(message)
                .call()
                .content();

        System.out.println("Response = " + response);
        System.out.println("Time = "
                + (System.currentTimeMillis() - start) + " ms");
        System.out.println("After Chat");
        System.out.println("==========================");

        return response;
    }

    // Hybrid Flow
    public HybridResponse analyzeIncident(String incidentNumber) {

        return orchestrator.analyzeIncident(incidentNumber);

    }

}