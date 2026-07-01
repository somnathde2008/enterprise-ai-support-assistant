package com.enterprise.ai.orchestrator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enterprise.ai.dto.IncidentAnalysisResponse;
import com.enterprise.ai.model.KnowledgeArticle;
import com.enterprise.ai.model.Recommendation;
import com.enterprise.ai.service.MockKnowledgeBaseService;
import com.enterprise.ai.service.MockRecommendationService;
import com.enterprise.ai.tool.IncidentTool;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AIOrchestrator {

    private static final Logger log =
            LoggerFactory.getLogger(AIOrchestrator.class);

    private final IncidentTool incidentTool;

    private final MockKnowledgeBaseService knowledgeService;

    private final MockRecommendationService recommendationService;

    private final ChatClient jsonChatClient;

    private final ObjectMapper objectMapper;

    public AIOrchestrator(

            @Qualifier("jsonChatClient")
            ChatClient jsonChatClient,

            IncidentTool incidentTool,

            MockKnowledgeBaseService knowledgeService,

            MockRecommendationService recommendationService,

            ObjectMapper objectMapper) {

        this.jsonChatClient = jsonChatClient;
        this.incidentTool = incidentTool;
        this.knowledgeService = knowledgeService;
        this.recommendationService = recommendationService;
        this.objectMapper = objectMapper;
    }

    public HybridResponse analyzeIncident(String incidentNumber) {

        log.info("Starting Hybrid Analysis for Incident : {}", incidentNumber);

        HybridResponse response = new HybridResponse();

        response.setIncidentNumber(incidentNumber);

        try {

            String prompt = incidentTool.analyzeIncident(incidentNumber);

            long aiStart = System.currentTimeMillis();

            log.info("Calling AI Model...");

            String aiResponse = jsonChatClient
                    .prompt()
                    .user(prompt)
                    .call()
                    .content();

            log.info("AI completed in {} ms",
                    System.currentTimeMillis() - aiStart);

            IncidentAnalysisResponse analysis =
                    objectMapper.readValue(
                            aiResponse,
                            IncidentAnalysisResponse.class);

            response.setAnalysis(analysis);

            long kbStart = System.currentTimeMillis();

            List<KnowledgeArticle> articles =
                    knowledgeService.searchArticlesByCategory(
                            analysis.getCategory());

            response.setKnowledgeArticles(articles);

            log.info("Knowledge Search completed in {} ms",
                    System.currentTimeMillis() - kbStart);

            long recStart = System.currentTimeMillis();

            Recommendation recommendation =
                    recommendationService.getRecommendation(

                            analysis.getCategory(),

                            analysis.getConfidence(),

                            analysis.getRootCause(),

                            analysis.getEscalationRequired());

            response.setRecommendation(recommendation);

            log.info("Recommendation generated in {} ms",
                    System.currentTimeMillis() - recStart);

            log.info("Hybrid Analysis completed successfully.");

            return response;

        } catch (Exception ex) {

            log.error("Hybrid Analysis Failed for Incident : {}",
                    incidentNumber,
                    ex);

            throw new RuntimeException(
                    "AI analysis failed",
                    ex);
        }
    }

}