/* ===========================================================
   Version : V5
   Description : Insert Sample Knowledge Articles
   =========================================================== */

INSERT INTO knowledge_articles
(
    kb_number,
    title,
    category,
    product_name,
    keywords,
    content,
    source_type,
    source_reference_id,
    source_url,
    chroma_document_id,
    embedding_status,
    embedding_model,
    article_version,
    active
)
VALUES
(
    'KB1001',
    'Azure SQL Connectivity',
    'Database',
    'Azure SQL',
    'azure sql,database,connectivity,firewall,1433',
    'Verify Azure SQL firewall. Verify connection string. Verify database availability. Test connectivity on port 1433.',
    'MANUAL',
    'KB1001',
    NULL,
    NULL,
    0,
    'nomic-embed-text',
    1,
    1
);

INSERT INTO knowledge_articles
(
    kb_number,
    title,
    category,
    product_name,
    keywords,
    content,
    source_type,
    source_reference_id,
    source_url,
    chroma_document_id,
    embedding_status,
    embedding_model,
    article_version,
    active
)
VALUES
(
    'KB1002',
    'Kafka Consumer Down',
    'Integration',
    'Apache Kafka',
    'kafka,consumer,broker,lag',
    'Restart Kafka consumer. Verify Kafka broker availability. Check consumer group lag. Review application logs.',
    'MANUAL',
    'KB1002',
    NULL,
    NULL,
    0,
    'nomic-embed-text',
    1,
    1
);

INSERT INTO knowledge_articles
(
    kb_number,
    title,
    category,
    product_name,
    keywords,
    content,
    source_type,
    source_reference_id,
    source_url,
    chroma_document_id,
    embedding_status,
    embedding_model,
    article_version,
    active
)
VALUES
(
    'KB1003',
    'Java OutOfMemoryError',
    'Application',
    'Java',
    'java,heap,memory,oom,jvm',
    'Collect heap dump. Analyze memory leak. Increase JVM heap size if required. Restart the application.',
    'MANUAL',
    'KB1003',
    NULL,
    NULL,
    0,
    'nomic-embed-text',
    1,
    1
);

PRINT 'V5 Sample Knowledge Inserted Successfully';