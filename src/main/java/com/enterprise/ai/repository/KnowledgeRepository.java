package com.enterprise.ai.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enterprise.ai.mapper.KnowledgeArticleRowMapper;
import com.enterprise.ai.model.KnowledgeArticle;

@Repository
public class KnowledgeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final KnowledgeArticleRowMapper rowMapper;

    public KnowledgeRepository(JdbcTemplate jdbcTemplate,
                               KnowledgeArticleRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    /**
     * Fetch all active knowledge articles
     */
    public List<KnowledgeArticle> findAll() {

        String sql = """
                SELECT *
                FROM knowledge_articles
                WHERE active = 1
                ORDER BY kb_number
                """;

        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * Search by Knowledge Number
     */
    public KnowledgeArticle findByKbNumber(String kbNumber) {

        String sql = """
                SELECT *
                FROM knowledge_articles
                WHERE kb_number = ?
                """;

        List<KnowledgeArticle> articles =
                jdbcTemplate.query(sql, rowMapper, kbNumber);

        return articles.isEmpty() ? null : articles.get(0);
    }

    /**
     * Search using title/category/content/keywords
     */
    public List<KnowledgeArticle> search(String keyword) {

        String searchKeyword = "%" + keyword.toLowerCase() + "%";

        String sql = """
                SELECT *
                FROM knowledge_articles
                WHERE active = 1
                AND
                (
                    LOWER(title) LIKE ?
                    OR LOWER(category) LIKE ?
                    OR LOWER(keywords) LIKE ?
                    OR LOWER(content) LIKE ?
                )
                ORDER BY kb_number
                """;

        return jdbcTemplate.query(
                sql,
                rowMapper,
                searchKeyword,
                searchKeyword,
                searchKeyword,
                searchKeyword);
    }

    /**
     * Search by Category
     */
    public List<KnowledgeArticle> findByCategory(String category) {

        String sql = """
                SELECT *
                FROM knowledge_articles
                WHERE active = 1
                AND LOWER(category)=LOWER(?)
                ORDER BY kb_number
                """;

        return jdbcTemplate.query(sql, rowMapper, category);
    }

    /**
     * Search by Source
     * Example:
     * GLPI
     * SERVICENOW
     * PDF
     * MANUAL
     */
    public List<KnowledgeArticle> findBySource(String sourceType) {

        String sql = """
                SELECT *
                FROM knowledge_articles
                WHERE active = 1
                AND LOWER(source_type)=LOWER(?)
                ORDER BY kb_number
                """;

        return jdbcTemplate.query(sql, rowMapper, sourceType);
    }

    /**
     * Fetch only active articles
     */
    public List<KnowledgeArticle> findActiveKnowledge() {

        String sql = """
                SELECT *
                FROM knowledge_articles
                WHERE active = 1
                ORDER BY title
                """;

        return jdbcTemplate.query(sql, rowMapper);
    }

}