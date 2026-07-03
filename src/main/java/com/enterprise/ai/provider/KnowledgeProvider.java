package com.enterprise.ai.provider;

import java.util.List;

import com.enterprise.ai.model.KnowledgeArticle;

public interface KnowledgeProvider {

    List<KnowledgeArticle> searchKnowledge(String keyword);

    KnowledgeArticle getKnowledgeArticle(Long articleId);

}