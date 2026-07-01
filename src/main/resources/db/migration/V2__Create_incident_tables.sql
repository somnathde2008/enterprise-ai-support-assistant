/* ===========================================================
   Enterprise AI Support Assistant
   Version     : V2
   Description : Create Incident Table
   =========================================================== */

CREATE TABLE incidents
(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,

    ---------------------------------------------------------
    -- Ticket Information
    ---------------------------------------------------------
    ticket_number NVARCHAR(100) NOT NULL UNIQUE,

    ticket_system NVARCHAR(50) NOT NULL,
        -- GLPI
        -- SERVICENOW
        -- JIRA
        -- FRESHSERVICE
        -- MANUAL

    source_reference_id NVARCHAR(100),

    short_description NVARCHAR(500) NOT NULL,

    description NVARCHAR(MAX),

    category NVARCHAR(100),

    priority NVARCHAR(50),

    status NVARCHAR(50),

    assignment_group NVARCHAR(100),

    assigned_to NVARCHAR(100),

    resolution NVARCHAR(MAX),

    ---------------------------------------------------------
    -- AI Analysis
    ---------------------------------------------------------
    ai_category NVARCHAR(100),

    confidence_score DECIMAL(5,2),

    recommendation_generated BIT NOT NULL DEFAULT 0,

    ---------------------------------------------------------
    -- Audit
    ---------------------------------------------------------
    created_date DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),

    updated_date DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),

    resolved_date DATETIME2 NULL
);

CREATE INDEX IDX_INCIDENT_NUMBER
ON incidents(ticket_number);

CREATE INDEX IDX_INCIDENT_SYSTEM
ON incidents(ticket_system);

CREATE INDEX IDX_INCIDENT_STATUS
ON incidents(status);

PRINT 'V2 Incident Table Created Successfully';