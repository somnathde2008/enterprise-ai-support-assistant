package com.enterprise.ai.model;

import java.time.LocalDateTime;

public class KnowledgeArticle {

    private Long id;

    // GLPI Knowledge Base ID
    private String kbNumber;

    // GLPI Internal ID (Future Sync)
    private Long glpiKnowledgeId;

    private String title;

    private String category;

    private String productName;

    private String keywords;

    // Full KB Content
    private String content;

    // GLPI / MANUAL / SERVICENOW
    private String sourceType;

    // ChromaDB embedding generated?
    private Boolean embeddingStatus;

    private Boolean active;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public KnowledgeArticle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKbNumber() {
        return kbNumber;
    }

    public void setKbNumber(String kbNumber) {
        this.kbNumber = kbNumber;
    }

    public Long getGlpiKnowledgeId() {
        return glpiKnowledgeId;
    }

    public void setGlpiKnowledgeId(Long glpiKnowledgeId) {
        this.glpiKnowledgeId = glpiKnowledgeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Boolean getEmbeddingStatus() {
        return embeddingStatus;
    }

    public void setEmbeddingStatus(Boolean embeddingStatus) {
        this.embeddingStatus = embeddingStatus;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}