package com.enterprise.ai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.enterprise.ai.model.KnowledgeArticle;

@Service
public class MockKnowledgeBaseService {

    private final List<KnowledgeArticle> articles = new ArrayList<>();

    public MockKnowledgeBaseService() {

        addArticle(
                "KB1001",
                "Azure SQL Connectivity",
                "Database",
                """
                Verify Azure SQL firewall.

                Verify connection string.

                Verify database status.

                Test connectivity on port 1433.
                """);

        addArticle(
                "KB1002",
                "Kafka Consumer Down",
                "Integration",
                """
                Restart Kafka Consumer.

                Verify Kafka Broker.

                Check Consumer Group Lag.

                Review application logs.
                """);

        addArticle(
                "KB1003",
                "Java OutOfMemoryError",
                "Application",
                """
                Collect Heap Dump.

                Analyze Memory Leak.

                Increase JVM Heap.

                Restart Application.
                """);

    }

    private void addArticle(
            String number,
            String title,
            String category,
            String resolution) {

        KnowledgeArticle article = new KnowledgeArticle();

        article.setArticleNumber(number);
        article.setTitle(title);
        article.setCategory(category);
        article.setResolution(resolution);

        articles.add(article);

    }

    /**
     * Hybrid AI uses this
     */
    public List<KnowledgeArticle> searchArticlesByCategory(String category) {

        List<KnowledgeArticle> result = new ArrayList<>();

        for (KnowledgeArticle article : articles) {

            if (article.getCategory()
                    .equalsIgnoreCase(category)) {

                result.add(article);

            }

        }

        return result;

    }

    /**
     * Chatbot keyword search
     */
    public List<KnowledgeArticle> searchArticles(String keyword) {

        List<KnowledgeArticle> result = new ArrayList<>();

        String search = keyword.toLowerCase();

        for (KnowledgeArticle article : articles) {

            if (article.getTitle()
                    .toLowerCase()
                    .contains(search)

                    ||

                    article.getCategory()
                            .toLowerCase()
                            .contains(search)

                    ||

                    article.getResolution()
                            .toLowerCase()
                            .contains(search)) {

                result.add(article);

            }

        }

        return result;

    }

}