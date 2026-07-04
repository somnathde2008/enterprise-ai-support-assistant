package com.enterprise.ai.controller;

import org.springframework.web.bind.annotation.*;

import com.enterprise.ai.dto.ChatRequest;
import com.enterprise.ai.orchestrator.AnalysisResponse;
import com.enterprise.ai.service.ChatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/chat")
//@Tag(name = "TPMS AI Chatbot")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * AI Tool Calling Endpoint
     * Uses Spring AI + Ollama + Tool Calling
     */
    @PostMapping
    public String chat(@RequestBody ChatRequest request) {

        return chatService.chat(request.getMessage());

    }

    /**
     * Hybrid AI Orchestrator Endpoint
     */
    //@Operation(summary = "Analyze TPMS Incident")
    @PostMapping("/hybrid/analyze")
    public AnalysisResponse analyzeIncident(
            @RequestParam String incidentNumber) {

        return chatService.analyzeIncident(incidentNumber);

    }

}