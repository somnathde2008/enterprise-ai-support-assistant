package com.enterprise.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enterprise.ai.model.KnowledgeArticle;
import com.enterprise.ai.repository.KnowledgeRepository;

@Service
public class KnowledgeService {

    private final KnowledgeRepository repository;

    public KnowledgeService(KnowledgeRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all active knowledge articles
     */
    public List<KnowledgeArticle> getAllKnowledgeArticles() {
        return repository.findAll();
    }

    /**
     * Search knowledge by keyword
     */
    public List<KnowledgeArticle> searchArticles(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            return repository.findAll();
        }

        return repository.search(keyword);
    }

    /**
     * Search knowledge by incident category
     */
    public List<KnowledgeArticle> searchArticlesByCategory(String category) {

        if (category == null || category.isBlank()) {
            return List.of();
        }

        return repository.findByCategory(category);
    }

    /**
     * Get Knowledge by KB Number
     */
    public KnowledgeArticle getKnowledgeByKbNumber(String kbNumber) {

        if (kbNumber == null || kbNumber.isBlank()) {
            return null;
        }

        return repository.findByKbNumber(kbNumber);
    }

    /**
     * Get knowledge by source
     * Example:
     * GLPI
     * SERVICENOW
     * PDF
     * MANUAL
     */
    public List<KnowledgeArticle> getKnowledgeBySource(String sourceType) {

        if (sourceType == null || sourceType.isBlank()) {
            return List.of();
        }

        return repository.findBySource(sourceType);
    }
}