package com.enterprise.ai.tool;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.KnowledgeArticle;
import com.enterprise.ai.service.KnowledgeService;

@Component
public class KnowledgeTool {

    private final KnowledgeService knowledgeService;

    public KnowledgeTool(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @Tool(description = """
            Search Knowledge Base.

            Input:
            - Incident Category
            - Incident Description
            - Error Message
            - KB Number

            Returns:
            - Knowledge Article
            - KB Number
            - Troubleshooting Steps
            - Resolution
            """)
    public List<KnowledgeArticle> searchKnowledge(String keyword) {

        return knowledgeService.searchArticles(keyword);

    }

}