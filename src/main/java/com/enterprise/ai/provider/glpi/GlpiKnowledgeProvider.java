package com.enterprise.ai.provider.glpi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.enterprise.ai.model.KnowledgeArticle;
import com.enterprise.ai.provider.KnowledgeProvider;

@Component
public class GlpiKnowledgeProvider
        implements KnowledgeProvider {

    private final GlpiKnowledgeClient client;

    public GlpiKnowledgeProvider(
            GlpiKnowledgeClient client) {

        this.client = client;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<KnowledgeArticle> searchKnowledge(
            String keyword) {

        Map<String,Object> response =
                client.searchKnowledge(keyword);

        if (response == null
                || !response.containsKey("data")) {

            return Collections.emptyList();
        }

        List<Map<String,Object>> data =
                (List<Map<String,Object>>) response.get("data");

        List<KnowledgeArticle> result =
                new ArrayList<>();

        for (Map<String,Object> row : data) {

            KnowledgeArticle article =
                    new KnowledgeArticle();

            article.setId(
                    Long.valueOf(
                            row.get("2").toString()));

            article.setTitle(
                    String.valueOf(
                            row.get("1")));

            result.add(article);
        }

        return result;
    }

    @Override
    public KnowledgeArticle getKnowledgeArticle(
            Long articleId) {

        Map<String,Object> article =
                client.getKnowledge(articleId);

        KnowledgeArticle kb =
                new KnowledgeArticle();

        kb.setId(articleId);

        kb.setTitle(
                String.valueOf(article.get("name")));

        kb.setContent(
                String.valueOf(article.get("answer")));

        return kb;
    }

}