package com.enterprise.ai.tool;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.KnowledgeArticle;
import com.enterprise.ai.service.MockKnowledgeBaseService;

@Component
public class KnowledgeTool {

    private final MockKnowledgeBaseService service;

    public KnowledgeTool(
            MockKnowledgeBaseService service) {

        this.service = service;

    }

    @Tool(description="""
    		Search TPMS Knowledge Base.

    		Input:

    		Incident Category

    		or

    		Incident Description

    		Return

    		KB ID

    		Runbook

    		Troubleshooting Steps

    		Resolution
    		""")
    public List<KnowledgeArticle> searchKnowledge(
            String keyword) {

        return service.searchArticles(keyword);

    }

}