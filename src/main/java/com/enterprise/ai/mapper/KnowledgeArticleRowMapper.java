package com.enterprise.ai.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.KnowledgeArticle;

@Component
public class KnowledgeArticleRowMapper implements RowMapper<KnowledgeArticle> {

    @Override
    public KnowledgeArticle mapRow(ResultSet rs, int rowNum) throws SQLException {

        KnowledgeArticle article = new KnowledgeArticle();

        article.setId(rs.getLong("id"));
        article.setKbNumber(rs.getString("kb_number"));

        // Future GLPI Integration
        try {
            article.setGlpiKnowledgeId(rs.getLong("glpi_knowledge_id"));
        } catch (SQLException ex) {
            // Column may not exist yet
        }

        article.setTitle(rs.getString("title"));
        article.setCategory(rs.getString("category"));
        article.setProductName(rs.getString("product_name"));
        article.setKeywords(rs.getString("keywords"));
        article.setContent(rs.getString("content"));
        article.setSourceType(rs.getString("source_type"));
        article.setEmbeddingStatus(rs.getBoolean("embedding_status"));
        article.setActive(rs.getBoolean("active"));

        // Future timestamps
        try {
            Timestamp created = rs.getTimestamp("created_date");
            if (created != null) {
                article.setCreatedDate(created.toLocalDateTime());
            }

            Timestamp updated = rs.getTimestamp("updated_date");
            if (updated != null) {
                article.setUpdatedDate(updated.toLocalDateTime());
            }
        } catch (SQLException ex) {
            // Columns may not exist yet
        }

        return article;
    }
}