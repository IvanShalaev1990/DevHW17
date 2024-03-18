INSERT INTO users (USER_NAME) VALUES
('First User'),
('Second User'),
('Third User'),
('Forth User'),
('Fifth User');

INSERT INTO users (USER_NAME, PASSWORD, ROLE) VALUES
('user', '{noop}jdbcDefault', 'ADMIN');