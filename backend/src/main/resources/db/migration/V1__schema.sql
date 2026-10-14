-- ============================================================
-- RIGHTSFLOW DATABASE SCHEMA MIGRATION V1
-- ============================================================

-- Attempt to create pgvector extension if supported
DO $$
BEGIN
    CREATE EXTENSION IF NOT EXISTS vector;
EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'pgvector extension not installed or permission denied; continuing with standard columns';
END $$;

-- 1. ROLES TABLE
CREATE TABLE IF NOT EXISTS roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 2. USERS TABLE
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(30),
    password_hash VARCHAR(255) NOT NULL,
    preferred_language VARCHAR(20) DEFAULT 'en',
    state_location VARCHAR(100),
    status VARCHAR(30) DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED')),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 3. USER_ROLES (M:N)
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- 4. USER_PREFERENCES
CREATE TABLE IF NOT EXISTS user_preferences (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    legal_interests TEXT, -- comma-separated or JSON list of category IDs/names
    email_notifications BOOLEAN DEFAULT TRUE,
    sms_notifications BOOLEAN DEFAULT FALSE,
    case_updates BOOLEAN DEFAULT TRUE,
    legal_alerts BOOLEAN DEFAULT TRUE,
    voice_assistant_enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 5. LEGAL_CATEGORIES
CREATE TABLE IF NOT EXISTS legal_categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    icon_name VARCHAR(50) DEFAULT 'Scale',
    color_theme VARCHAR(50) DEFAULT 'blue',
    display_order INT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 6. LEGAL_SOURCES
CREATE TABLE IF NOT EXISTS legal_sources (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    source_type VARCHAR(50) NOT NULL, -- INDIA_CODE, NALSA, ECOURTS, CONSUMER_HELPLINE, MINISTRY_PORTAL, OTHER
    official_url VARCHAR(500),
    authority_name VARCHAR(200) NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 7. LAWS TABLE
CREATE TABLE IF NOT EXISTS laws (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    short_code VARCHAR(50),
    year INT,
    enactment_date DATE,
    jurisdiction VARCHAR(100) DEFAULT 'India',
    description TEXT,
    source_id BIGINT REFERENCES legal_sources(id) ON DELETE SET NULL,
    official_link VARCHAR(500),
    status VARCHAR(30) DEFAULT 'ACTIVE',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 8. LAW_PROVISIONS (Sections, Articles, Rules)
CREATE TABLE IF NOT EXISTS law_provisions (
    id BIGSERIAL PRIMARY KEY,
    law_id BIGINT NOT NULL REFERENCES laws(id) ON DELETE CASCADE,
    section_number VARCHAR(100) NOT NULL,
    title VARCHAR(255) NOT NULL,
    simple_summary TEXT,
    full_text TEXT,
    penalty_or_remedy TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 9. RIGHTS TABLE (Curated Citizen Legal Rights)
CREATE TABLE IF NOT EXISTS rights (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT NOT NULL REFERENCES legal_categories(id) ON DELETE RESTRICT,
    title VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL UNIQUE,
    short_description TEXT NOT NULL,
    simple_explanation TEXT NOT NULL,
    detailed_explanation TEXT,
    who_it_applies_to TEXT,
    why_it_applies TEXT,
    real_world_example TEXT,
    recommended_actions TEXT,
    required_documents TEXT,
    relevant_authority VARCHAR(255),
    relevant_service VARCHAR(255),
    procedure_summary TEXT,
    official_source_url VARCHAR(500),
    source_authority VARCHAR(255),
    last_verified DATE DEFAULT CURRENT_DATE,
    effective_from DATE DEFAULT CURRENT_DATE,
    effective_to DATE,
    version INT DEFAULT 1,
    verification_status VARCHAR(30) DEFAULT 'VERIFIED' CHECK (verification_status IN ('PENDING', 'VERIFIED', 'REJECTED', 'OUTDATED')),
    content_status VARCHAR(30) DEFAULT 'PUBLISHED' CHECK (content_status IN ('DRAFT', 'UNDER_REVIEW', 'PUBLISHED', 'ARCHIVED')),
    view_count BIGINT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 10. RIGHT_LAWS (M:N linking Rights to Laws)
CREATE TABLE IF NOT EXISTS right_laws (
    right_id BIGINT NOT NULL REFERENCES rights(id) ON DELETE CASCADE,
    law_id BIGINT NOT NULL REFERENCES laws(id) ON DELETE CASCADE,
    PRIMARY KEY (right_id, law_id)
);

-- 11. ISSUES TABLE
CREATE TABLE IF NOT EXISTS issues (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT NOT NULL REFERENCES legal_categories(id) ON DELETE RESTRICT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 12. ISSUE_RIGHTS (M:N)
CREATE TABLE IF NOT EXISTS issue_rights (
    issue_id BIGINT NOT NULL REFERENCES issues(id) ON DELETE CASCADE,
    right_id BIGINT NOT NULL REFERENCES rights(id) ON DELETE CASCADE,
    PRIMARY KEY (issue_id, right_id)
);

-- 13. LEGAL_VERSIONS (Content versioning history)
CREATE TABLE IF NOT EXISTS legal_versions (
    id BIGSERIAL PRIMARY KEY,
    right_id BIGINT NOT NULL REFERENCES rights(id) ON DELETE CASCADE,
    version INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    simple_explanation TEXT,
    detailed_explanation TEXT,
    changed_by BIGINT REFERENCES users(id) ON DELETE SET NULL,
    change_summary VARCHAR(500),
    verification_status VARCHAR(30) DEFAULT 'VERIFIED',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 14. AUTHORITIES
CREATE TABLE IF NOT EXISTS authorities (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    jurisdiction_level VARCHAR(50) DEFAULT 'National', -- National, State, District, Local
    contact_phone VARCHAR(100),
    contact_email VARCHAR(150),
    official_website VARCHAR(500),
    physical_address TEXT,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 15. LEGAL_SERVICES
CREATE TABLE IF NOT EXISTS legal_services (
    id BIGSERIAL PRIMARY KEY,
    authority_id BIGINT REFERENCES authorities(id) ON DELETE SET NULL,
    category_id BIGINT REFERENCES legal_categories(id) ON DELETE SET NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    eligibility_criteria TEXT,
    online_portal_url VARCHAR(500),
    fee_details VARCHAR(255) DEFAULT 'Free of Charge',
    processing_time_estimate VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 16. SERVICE_STEPS
CREATE TABLE IF NOT EXISTS service_steps (
    id BIGSERIAL PRIMARY KEY,
    service_id BIGINT NOT NULL REFERENCES legal_services(id) ON DELETE CASCADE,
    step_number INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    instruction TEXT NOT NULL,
    estimated_duration VARCHAR(100)
);

-- 17. SERVICE_DOCUMENTS
CREATE TABLE IF NOT EXISTS service_documents (
    id BIGSERIAL PRIMARY KEY,
    service_id BIGINT NOT NULL REFERENCES legal_services(id) ON DELETE CASCADE,
    document_name VARCHAR(255) NOT NULL,
    is_mandatory BOOLEAN DEFAULT TRUE,
    description TEXT
);

-- 18. FAQS
CREATE TABLE IF NOT EXISTS faqs (
    id BIGSERIAL PRIMARY KEY,
    right_id BIGINT REFERENCES rights(id) ON DELETE CASCADE,
    category_id BIGINT REFERENCES legal_categories(id) ON DELETE SET NULL,
    question VARCHAR(500) NOT NULL,
    answer TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 19. EXAMPLES
CREATE TABLE IF NOT EXISTS examples (
    id BIGSERIAL PRIMARY KEY,
    right_id BIGINT NOT NULL REFERENCES rights(id) ON DELETE CASCADE,
    scenario_title VARCHAR(255) NOT NULL,
    scenario_description TEXT NOT NULL,
    solution_outcome TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 20. CASES TABLE
CREATE TABLE IF NOT EXISTS cases (
    id BIGSERIAL PRIMARY KEY,
    case_number VARCHAR(50) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    category_id BIGINT REFERENCES legal_categories(id) ON DELETE SET NULL,
    right_id BIGINT REFERENCES rights(id) ON DELETE SET NULL,
    title VARCHAR(255) NOT NULL,
    problem_description TEXT NOT NULL,
    related_laws TEXT,
    related_provisions TEXT,
    authority_name VARCHAR(255),
    service_name VARCHAR(255),
    status VARCHAR(50) DEFAULT 'CREATED' CHECK (status IN (
        'CREATED', 'INFORMATION_REVIEWED', 'DOCUMENTS_PENDING',
        'READY_FOR_ACTION', 'ACTION_TAKEN', 'SUBMITTED',
        'UNDER_REVIEW', 'RESOLVED', 'CLOSED'
    )),
    notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 21. CASE_STATUS_HISTORY
CREATE TABLE IF NOT EXISTS case_status_history (
    id BIGSERIAL PRIMARY KEY,
    case_id BIGINT NOT NULL REFERENCES cases(id) ON DELETE CASCADE,
    from_status VARCHAR(50),
    to_status VARCHAR(50) NOT NULL,
    changed_by BIGINT REFERENCES users(id) ON DELETE SET NULL,
    remarks TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 22. CASE_ACTIONS
CREATE TABLE IF NOT EXISTS case_actions (
    id BIGSERIAL PRIMARY KEY,
    case_id BIGINT NOT NULL REFERENCES cases(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    action_type VARCHAR(50) DEFAULT 'ACTION', -- DOCUMENT, CONTACT, DRAFT, SUBMIT, TRACK
    status VARCHAR(50) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'SKIPPED')),
    due_date DATE,
    order_index INT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 23. DOCUMENTS TABLE
CREATE TABLE IF NOT EXISTS documents (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    file_name VARCHAR(255) NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(100) NOT NULL,
    file_size BIGINT NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 24. CASE_DOCUMENTS (M:N)
CREATE TABLE IF NOT EXISTS case_documents (
    id BIGSERIAL PRIMARY KEY,
    case_id BIGINT NOT NULL REFERENCES cases(id) ON DELETE CASCADE,
    document_id BIGINT NOT NULL REFERENCES documents(id) ON DELETE CASCADE,
    document_category VARCHAR(100) DEFAULT 'SUPPORTING_EVIDENCE',
    is_verified BOOLEAN DEFAULT FALSE,
    uploaded_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (case_id, document_id)
);

-- 25. NOTIFICATIONS TABLE
CREATE TABLE IF NOT EXISTS notifications (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    case_id BIGINT REFERENCES cases(id) ON DELETE SET NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(50) DEFAULT 'INFO', -- CASE_UPDATE, ACTION_REMINDER, LEGAL_ALERT, SYSTEM
    is_read BOOLEAN DEFAULT FALSE,
    action_url VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 26. LEGAL_UPDATES
CREATE TABLE IF NOT EXISTS legal_updates (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT REFERENCES legal_categories(id) ON DELETE SET NULL,
    right_id BIGINT REFERENCES rights(id) ON DELETE SET NULL,
    title VARCHAR(255) NOT NULL,
    summary TEXT NOT NULL,
    what_changed TEXT NOT NULL,
    who_is_affected TEXT,
    effective_date DATE NOT NULL,
    published_date DATE DEFAULT CURRENT_DATE,
    source_name VARCHAR(200),
    source_authority VARCHAR(200),
    official_url VARCHAR(500),
    last_verified DATE DEFAULT CURRENT_DATE,
    version INT DEFAULT 1,
    status VARCHAR(30) DEFAULT 'PUBLISHED' CHECK (status IN ('DRAFT', 'UNDER_REVIEW', 'PUBLISHED', 'ARCHIVED')),
    view_count BIGINT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 27. BOOKMARKS
CREATE TABLE IF NOT EXISTS bookmarks (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    right_id BIGINT NOT NULL REFERENCES rights(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, right_id)
);

-- 28. RECENTLY_VIEWED
CREATE TABLE IF NOT EXISTS recently_viewed (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    right_id BIGINT NOT NULL REFERENCES rights(id) ON DELETE CASCADE,
    viewed_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 29. SEARCH_HISTORY
CREATE TABLE IF NOT EXISTS search_history (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    query_text VARCHAR(500) NOT NULL,
    category_filter VARCHAR(100),
    results_count INT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 30. FEEDBACK
CREATE TABLE IF NOT EXISTS feedback (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    feedback_type VARCHAR(50) DEFAULT 'GENERAL', -- GENERAL, AI_ACCURACY, FEATURE_REQUEST, BUG
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comments TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 31. CONTENT_REPORTS
CREATE TABLE IF NOT EXISTS content_reports (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    right_id BIGINT REFERENCES rights(id) ON DELETE CASCADE,
    reason VARCHAR(255) NOT NULL,
    details TEXT,
    status VARCHAR(30) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'REVIEWED', 'RESOLVED', 'DISMISSED')),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 32. EMBEDDINGS (RAG Knowledge Chunks)
CREATE TABLE IF NOT EXISTS embeddings (
    id BIGSERIAL PRIMARY KEY,
    right_id BIGINT REFERENCES rights(id) ON DELETE CASCADE,
    source_type VARCHAR(50) DEFAULT 'RIGHT', -- RIGHT, LAW, PROVISION, UPDATE, FAQ
    content_chunk TEXT NOT NULL,
    metadata_json TEXT, -- category, title, section, url
    embedding_vector TEXT, -- Fallback text representation of float array or pgvector
    dimension INT DEFAULT 768,
    status VARCHAR(30) DEFAULT 'EMBEDDED' CHECK (status IN ('PENDING', 'EMBEDDED', 'FAILED', 'OUTDATED')),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 33. AUDIT_LOGS
CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGSERIAL PRIMARY KEY,
    actor_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    actor_email VARCHAR(150),
    action VARCHAR(100) NOT NULL,
    entity_name VARCHAR(100) NOT NULL,
    entity_id VARCHAR(100),
    details TEXT,
    ip_address VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- INDEXES FOR HIGH QUERY PERFORMANCE
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_rights_category_id ON rights(category_id);
CREATE INDEX IF NOT EXISTS idx_rights_slug ON rights(slug);
CREATE INDEX IF NOT EXISTS idx_rights_status ON rights(content_status, verification_status);
CREATE INDEX IF NOT EXISTS idx_cases_user_id ON cases(user_id);
CREATE INDEX IF NOT EXISTS idx_cases_status ON cases(status);
CREATE INDEX IF NOT EXISTS idx_notifications_user_id ON notifications(user_id, is_read);
CREATE INDEX IF NOT EXISTS idx_bookmarks_user_id ON bookmarks(user_id);
CREATE INDEX IF NOT EXISTS idx_legal_updates_cat ON legal_updates(category_id, status);
CREATE INDEX IF NOT EXISTS idx_audit_logs_created ON audit_logs(created_at DESC);
