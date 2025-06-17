CREATE DATABASE IF NOT EXISTS app_gerenciarods
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE app_gerenciarods;

CREATE TABLE IF NOT EXISTS tbl_users (
    user_id CHAR(36) PRIMARY KEY,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    birth_date DATE DEFAULT NULL,
    cpf VARCHAR(11) DEFAULT NULL,
    phone_number VARCHAR(20) DEFAULT NULL,
    address_line VARCHAR(150) DEFAULT NULL,
    city VARCHAR(100) DEFAULT NULL,
    state VARCHAR(100) DEFAULT NULL,
    postal_code VARCHAR(20) DEFAULT NULL,
    type ENUM('NORMAL', 'ADMIN') DEFAULT 'NORMAL',
    is_active BOOLEAN DEFAULT TRUE,
    is_profile_complete BOOLEAN DEFAULT FALSE,
    profile_photo_url VARCHAR(255) DEFAULT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tbl_password_recovery (
    recovery_id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    pin VARCHAR(6) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    is_used BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES tbl_users(user_id)
);

CREATE TABLE IF NOT EXISTS tbl_ods_topics (
    ods_id CHAR(36) PRIMARY KEY,
    ods_name VARCHAR(150) NOT NULL,
    ods_description TEXT
);

CREATE TABLE IF NOT EXISTS tbl_event_requests (
    request_id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    ods_id CHAR(36) NOT NULL,
    event_name VARCHAR(150) NOT NULL,
    event_description TEXT,
    event_date DATE NOT NULL,
    address_line VARCHAR(150) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'CANCELED') DEFAULT 'PENDING',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES tbl_users(user_id),
    FOREIGN KEY (ods_id) REFERENCES tbl_ods_topics(ods_id)
);
CREATE TABLE IF NOT EXISTS tbl_events (
    event_id CHAR(36) PRIMARY KEY,
    ods_id CHAR(36) NOT NULL,
    event_name VARCHAR(150) NOT NULL,
    event_description TEXT,
    event_date DATE NOT NULL,
    address_line VARCHAR(150) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    created_by CHAR(36) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ods_id) REFERENCES tbl_ods_topics(ods_id),
    FOREIGN KEY (created_by) REFERENCES tbl_users(user_id)
);

CREATE TABLE IF NOT EXISTS tbl_event_registrations (
    registration_id CHAR(36) PRIMARY KEY,
    event_id CHAR(36) NOT NULL,
    user_id CHAR(36) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES tbl_events(event_id),
    FOREIGN KEY (user_id) REFERENCES tbl_users(user_id)
);

INSERT INTO tbl_ods_topics (ods_id, ods_name, ods_description) VALUES
(UUID(), 'Erradicação da Pobreza', 'Acabar com a pobreza em todas as suas formas, em todos os lugares.'),
(UUID(), 'Fome Zero e Agricultura Sustentável', 'Acabar com a fome, alcançar a segurança alimentar e melhoria da nutrição e promover a agricultura sustentável.'),
(UUID(), 'Saúde e Bem-estar', 'Garantir uma vida saudável e promover o bem-estar para todos, em todas as idades.'),
(UUID(), 'Educação de Qualidade', 'Assegurar a educação inclusiva, equitativa e de qualidade, e promover oportunidades de aprendizagem ao longo da vida para todos.'),
(UUID(), 'Igualdade de Gênero', 'Alcançar a igualdade de gênero e empoderar todas as mulheres e meninas.'),
(UUID(), 'Água Potável e Saneamento', 'Garantir a disponibilidade e gestão sustentável da água e saneamento para todos.'),
(UUID(), 'Energia Acessível e Limpa', 'Garantir o acesso confiável, sustentável, moderno e a preço acessível à energia para todos.'),
(UUID(), 'Trabalho Decente e Crescimento Econômico', 'Promover o crescimento econômico sustentado, inclusivo e sustentável, emprego pleno e produtivo e trabalho decente para todos.'),
(UUID(), 'Indústria, Inovação e Infraestrutura', 'Construir infraestruturas resilientes, promover a industrialização inclusiva e sustentável e fomentar a inovação.'),
(UUID(), 'Redução das Desigualdades', 'Reduzir a desigualdade dentro dos países e entre eles.'),
(UUID(), 'Cidades e Comunidades Sustentáveis', 'Tornar as cidades e os assentamentos humanos inclusivos, seguros, resilientes e sustentáveis.'),
(UUID(), 'Consumo e Produção Responsáveis', 'Assegurar padrões de produção e de consumo sustentáveis.'),
(UUID(), 'Ação Contra a Mudança Global do Clima', 'Tomar medidas urgentes para combater a mudança climática e seus impactos.'),
(UUID(), 'Vida na Água', 'Conservar e promover o uso sustentável dos oceanos, mares e recursos marinhos para o desenvolvimento sustentável.'),
(UUID(), 'Vida Terrestre', 'Proteger, recuperar e promover o uso sustentável dos ecossistemas terrestres, gerir de forma sustentável as florestas, combater a desertificação, deter e reverter a degradação da terra e deter a perda de biodiversidade.'),
(UUID(), 'Paz, Justiça e Instituições Eficazes', 'Promover sociedades pacíficas e inclusivas para o desenvolvimento sustentável, proporcionar o acesso à justiça para todos e construir instituições eficazes, responsáveis e inclusivas em todos os níveis.'),
(UUID(), 'Parcerias e Meios de Implementação', 'Fortalecer os meios de implementação e revitalizar a parceria global para o desenvolvimento sustentável.');

INSERT INTO tbl_users (
    user_id,
    fullname,
    email,
    password_hash,
    type,
    is_active,
    is_profile_complete,
    create_date
) VALUES (
    UUID(),
    'Usuário Administrador',
    'admin@gmail.com',
    '$2a$12$/iHC8HVJZUL.rh3RunlF4eglj3fp2B.6NtUdQ8mj6B921keeZp6Vm',
    'ADMIN',
    TRUE,
    TRUE,
    CURRENT_TIMESTAMP
);
