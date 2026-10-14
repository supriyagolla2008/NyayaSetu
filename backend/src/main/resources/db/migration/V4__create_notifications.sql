CREATE TABLE IF NOT EXISTS notifications (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    title VARCHAR(255),
    message TEXT,
    type VARCHAR(50),
    is_read BOOLEAN DEFAULT FALSE,
    read_at TIMESTAMP,
    created_at TIMESTAMP,
    related_entity_type VARCHAR(100),
    related_entity_id BIGINT
);

CREATE TABLE IF NOT EXISTS notification_preferences (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    type VARCHAR(50),
    enabled BOOLEAN DEFAULT TRUE,
    email_enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS legal_updates (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    summary TEXT,
    what_changed TEXT,
    who_is_affected TEXT,
    effective_date TIMESTAMP,
    category_id BIGINT REFERENCES legal_categories(id),
    source_url VARCHAR(500),
    source_authority VARCHAR(255),
    published_date TIMESTAMP,
    last_verified TIMESTAMP,
    version INT,
    status VARCHAR(50),
    created_by BIGINT,
    created_at TIMESTAMP
);