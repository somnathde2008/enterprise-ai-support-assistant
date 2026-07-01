/* ===========================================================
   Enterprise AI Support Assistant
   Version     : V4
   Description : Create Knowledge Base Table
   =========================================================== */

CREATE TABLE knowledge_articles
(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,

    ---------------------------------------------------------
    -- Knowledge Information
    ---------------------------------------------------------
    kb_number NVARCHAR(50) NOT NULL UNIQUE,

    title NVARCHAR(300) NOT NULL,

    category NVARCHAR(100),

    product_name NVARCHAR(100),

    keywords NVARCHAR(1000),

    content NVARCHAR(MAX),

    ---------------------------------------------------------
    -- Source Information
    ---------------------------------------------------------
    source_type NVARCHAR(50) NOT NULL,
        -- Examples:
        -- GLPI
        -- SERVICENOW
        -- JIRA
        -- PDF
        -- MANUAL
        -- CONFLUENCE

    source_reference_id NVARCHAR(100),

    source_url NVARCHAR(500),

    ---------------------------------------------------------
    -- RAG / Embedding
    ---------------------------------------------------------
    chroma_document_id NVARCHAR(200),

    embedding_status BIT NOT NULL DEFAULT 0,

    embedding_model NVARCHAR(100),

    ---------------------------------------------------------
    -- Versioning
    ---------------------------------------------------------
    article_version INT NOT NULL DEFAULT 1,

    active BIT NOT NULL DEFAULT 1,

    ---------------------------------------------------------
    -- Audit
    ---------------------------------------------------------
    created_date DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),

    updated_date DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);

---------------------------------------------------------
-- Indexes
---------------------------------------------------------

CREATE INDEX IDX_KNOWLEDGE_KB_NUMBER
ON knowledge_articles(kb_number);

CREATE INDEX IDX_KNOWLEDGE_CATEGORY
ON knowledge_articles(category);

CREATE INDEX IDX_KNOWLEDGE_SOURCE
ON knowledge_articles(source_type);

CREATE INDEX IDX_KNOWLEDGE_ACTIVE
ON knowledge_articles(active);

PRINT 'V4 Knowledge Base Created Successfully';