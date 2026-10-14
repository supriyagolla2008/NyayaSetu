CREATE TABLE IF NOT EXISTS cases (
    id BIGSERIAL PRIMARY KEY,
    case_number VARCHAR(100) UNIQUE,
    user_id BIGINT REFERENCES users(id),
    title VARCHAR(255),
    description TEXT,
    category_id BIGINT REFERENCES legal_categories(id),
    status VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    resolved_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS case_status_history (
    id BIGSERIAL PRIMARY KEY,
    case_id BIGINT REFERENCES cases(id),
    from_status VARCHAR(50),
    to_status VARCHAR(50),
    changed_by BIGINT,
    changed_at TIMESTAMP,
    notes TEXT
);

CREATE TABLE IF NOT EXISTS case_actions (
    id BIGSERIAL PRIMARY KEY,
    case_id BIGINT REFERENCES cases(id),
    title VARCHAR(255),
    description TEXT,
    action_type VARCHAR(50),
    status VARCHAR(50),
    due_date TIMESTAMP,
    completed_at TIMESTAMP,
    display_order INT
);

CREATE TABLE IF NOT EXISTS documents (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    file_name VARCHAR(255),
    original_file_name VARCHAR(255),
    content_type VARCHAR(100),
    file_size BIGINT,
    storage_path TEXT,
    uploaded_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS case_documents (
    id BIGSERIAL PRIMARY KEY,
    case_id BIGINT REFERENCES cases(id),
    document_id BIGINT REFERENCES documents(id)
);