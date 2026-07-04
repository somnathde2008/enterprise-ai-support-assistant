package com.enterprise.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enterprise.ai.model.KnowledgeArticle;
import com.enterprise.ai.provider.KnowledgeProvider;

@Service
public class KnowledgeService {

    private final KnowledgeProvider knowledgeProvider;

    public KnowledgeService(KnowledgeProvider knowledgeProvider) {
        this.knowledgeProvider = knowledgeProvider;
    }

    /**
     * Search Knowledge from configured provider (GLPI)
     */
    public List<KnowledgeArticle> searchKnowledge(String keyword) {

        return knowledgeProvider.searchKnowledge(keyword);

    }

    /**
     * Get Knowledge Article
     */
    public KnowledgeArticle getKnowledgeArticle(Long articleId) {

        return knowledgeProvider.getKnowledgeArticle(articleId);

    }

}