/* ===========================================================
   Version : V3
   Description : Chat History
   =========================================================== */

CREATE TABLE chat_messages
(
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,

    session_id          BIGINT NOT NULL,

    message_role        NVARCHAR(20) NOT NULL,

    message             NVARCHAR(MAX) NOT NULL,

    token_count         INT DEFAULT 0,

    response_time_ms    INT DEFAULT 0,

    created_at          DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),

    CONSTRAINT FK_CHAT_MESSAGE_SESSION
        FOREIGN KEY(session_id)
        REFERENCES chat_sessions(id)
);

CREATE INDEX IDX_CHAT_SESSION
ON chat_messages(session_id);

PRINT 'V3 Chat History Created Successfully';