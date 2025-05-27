USE app_gerenciarods;

CREATE TABLE tbl_users (
    user_id CHAR(36) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    type ENUM('NORMAL', 'ADMIN') DEFAULT 'NORMAL',
    is_active BOOLEAN DEFAULT TRUE,
    profile_photo_url VARCHAR(255) DEFAULT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE tbl_password_recovery (
    recovery_id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    pin VARCHAR(6) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    is_used BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES tbl_users(user_id)
);
