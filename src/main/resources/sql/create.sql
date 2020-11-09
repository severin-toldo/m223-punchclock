INSERT INTO security_role (name) VALUES ('ADMIN');
INSERT INTO user (id, email, username, password) VALUES (1, 'admin@admin.com', 'admin', '$2a$10$8GF/pBJPLbAXrQXRMNeurOfZxQRtH/UXuiYZxSrbpTnStXB.ZooVa');
INSERT INTO user_security_roles (user_id, security_roles_id) VALUES (1, 1);