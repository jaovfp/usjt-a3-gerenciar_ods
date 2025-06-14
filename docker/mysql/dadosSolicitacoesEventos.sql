INSERT INTO tbl_event_requests
(request_id, user_id, ods_id, event_name, event_description, event_date, address_line, city, state, postal_code, status)
VALUES
-- 5 APPROVED
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Cultura 1', 'Descrição do evento 1', '2025-07-10', 'Rua A, 123', 'São Paulo', 'SP', '01000-000', 'APPROVED'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Tecnologia 2', 'Descrição do evento 2', '2025-08-15', 'Av. B, 456', 'Rio de Janeiro', 'RJ', '20000-000', 'APPROVED'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Educação 3', 'Descrição do evento 3', '2025-09-20', 'Rua C, 789', 'Belo Horizonte', 'MG', '30000-000', 'APPROVED'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Saúde 4', 'Descrição do evento 4', '2025-10-25', 'Av. D, 101', 'Curitiba', 'PR', '40000-000', 'APPROVED'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Esporte 5', 'Descrição do evento 5', '2025-11-30', 'Rua E, 202', 'Porto Alegre', 'RS', '50000-000', 'APPROVED'),

-- 2 PENDING
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Música 6', 'Descrição do evento 6', '2025-12-05', 'Av. F, 303', 'Fortaleza', 'CE', '60000-000', 'PENDING'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Arte 7', 'Descrição do evento 7', '2025-12-15', 'Rua G, 404', 'Salvador', 'BA', '70000-000', 'PENDING'),

-- 3 CANCELED
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Literatura 8', 'Descrição do evento 8', '2026-01-10', 'Av. H, 505', 'Brasília', 'DF', '80000-000', 'CANCELED'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Cinema 9', 'Descrição do evento 9', '2026-02-15', 'Rua I, 606', 'Manaus', 'AM', '90000-000', 'CANCELED'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Teatro 10', 'Descrição do evento 10', '2026-03-20', 'Av. J, 707', 'Recife', 'PE', '10000-000', 'CANCELED'),

-- 4 PENDING
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Ciência 11', 'Descrição do evento 11', '2026-04-25', 'Rua K, 808', 'Goiânia', 'GO', '11000-000', 'PENDING'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Tecnologia 12', 'Descrição do evento 12', '2026-05-30', 'Av. L, 909', 'Campinas', 'SP', '12000-000', 'PENDING'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Educação 13', 'Descrição do evento 13', '2026-06-15', 'Rua M, 1010', 'São Luís', 'MA', '13000-000', 'PENDING'),
(UUID(), '17cb53b8-ac5c-4b94-8ee4-cbeb8a59ca70', 'f9d2a5f5-45aa-11f0-9a00-92eed61eb9b0', 'Evento Saúde 14', 'Descrição do evento 14', '2026-07-20', 'Av. N, 1111', 'Teresina', 'PI', '14000-000', 'PENDING');
