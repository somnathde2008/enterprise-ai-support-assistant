/* ===========================================================
   Enterprise AI Support Assistant
   Version : V1
   Description : Initial Schema
   =========================================================== */

------------------------------------------------------------
-- USERS
------------------------------------------------------------
CREATE TABLE users
(
    id              BIGINT IDENTITY(1,1) PRIMARY KEY,

    username        NVARCHAR(100) NOT NULL UNIQUE,

    full_name       NVARCHAR(200) NOT NULL,

    email           NVARCHAR(255) NOT NULL UNIQUE,

    password_hash   NVARCHAR(500),

    role_name       NVARCHAR(50) NOT NULL,

    active          BIT NOT NULL DEFAULT 1,

    created_at      DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),

    updated_at      DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);

------------------------------------------------------------
-- CHAT SESSIONS
------------------------------------------------------------
CREATE TABLE chat_sessions
(
    id              BIGINT IDENTITY(1,1) PRIMARY KEY,

    session_id      UNIQUEIDENTIFIER NOT NULL DEFAULT NEWID(),

    user_id         BIGINT NOT NULL,

    title           NVARCHAR(250),

    created_at      DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),

    updated_at      DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),

    CONSTRAINT FK_CHAT_SESSION_USER
        FOREIGN KEY(user_id)
        REFERENCES users(id)
);

------------------------------------------------------------
-- FLYWAY NOTE
------------------------------------------------------------
PRINT 'V1 Initial Schema Created Successfully';