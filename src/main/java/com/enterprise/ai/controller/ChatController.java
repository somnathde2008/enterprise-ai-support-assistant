package com.enterprise.ai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enterprise.ai.dto.ChatRequest;
import com.enterprise.ai.orchestrator.AnalysisResponse;
import com.enterprise.ai.service.ChatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * AI Chat Endpoint.
     *
     * Supports Spring AI Tool Calling.
     *
     * @param request Chat Request
     * @return AI Response
     */
    @PostMapping
    public ResponseEntity<String> chat(
            @Valid @RequestBody ChatRequest request) {

        LOGGER.info("Received AI chat request.");

        String response =
                chatService.chat(
                        request.getMessage());

        LOGGER.info("AI chat request completed successfully.");

        return ResponseEntity.ok(response);

    }

    /**
     * Hybrid AI Incident Analysis.
     *
     * @param incidentNumber Incident Number
     * @return Hybrid Analysis Response
     */
    @PostMapping("/hybrid/analyze")
    public ResponseEntity<AnalysisResponse> analyzeIncident(
            @RequestParam String incidentNumber) {

        LOGGER.info(
                "Received Hybrid Incident Analysis request for Incident={}",
                incidentNumber);

        AnalysisResponse response =
                chatService.analyzeIncident(
                        incidentNumber);

        LOGGER.info(
                "Hybrid Incident Analysis completed successfully.");

        return ResponseEntity.ok(response);

    }

}