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

            Use this tool to search knowledge articles,
            troubleshooting guides,
            known errors,
            workarounds,
            and resolutions.

            Input:
            - Incident Category
            - Incident Description
            - Error Message
            - KB Number

            Returns:
            - Matching Knowledge Articles
            - Resolution
            - Troubleshooting Steps
            """)
    public List<KnowledgeArticle> searchKnowledge(
            String keyword) {

        return knowledgeService.searchKnowledge(keyword);

    }

}