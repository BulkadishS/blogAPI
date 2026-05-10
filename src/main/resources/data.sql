INSERT INTO roles (name)
VALUES ('USER'), ('ADMIN')
ON CONFLICT (name) DO NOTHING;

INSERT INTO users (id, username, password)
VALUES (5, 'root', '$2a$10$yykkJnk3vAbsWMT8bWkrgepQFAz4ssL0ywPQLIJOChbj6wAb1VbFW');

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r
WHERE u.id = 5 AND r.name = 'ADMIN'
ON CONFLICT DO NOTHING;