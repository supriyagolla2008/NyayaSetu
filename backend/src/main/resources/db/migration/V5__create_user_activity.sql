CREATE TABLE IF NOT EXISTS bookmarks (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    entity_type VARCHAR(50),
    entity_id BIGINT,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS recently_viewed (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    entity_type VARCHAR(50),
    entity_id BIGINT,
    viewed_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS search_history (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    query TEXT,
    language VARCHAR(10),
    result_count INT,
    searched_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS feedback (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    type VARCHAR(50),
    rating INT,
    comment TEXT,
    related_entity_type VARCHAR(100),
    related_entity_id BIGINT,
    status VARCHAR(50),
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS content_reports (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    reported_entity_type VARCHAR(50),
    reported_entity_id BIGINT,
    reason VARCHAR(255),
    details TEXT,
    status VARCHAR(50),
    created_at TIMESTAMP
);