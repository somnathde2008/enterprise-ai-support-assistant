package com.enterprise.ai.provider.glpi;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GlpiKnowledgeClient {

    private final GlpiApiClient glpiApiClient;

    public GlpiKnowledgeClient(
            GlpiApiClient glpiApiClient) {

        this.glpiApiClient = glpiApiClient;
    }

    public Map<String, Object> searchKnowledge(
            String keyword) {

        return glpiApiClient.searchKnowledge(
                keyword);
    }

    public Map<String, Object> getKnowledge(
            Long id) {

        return glpiApiClient.getKnowledge(
                id);
    }

}