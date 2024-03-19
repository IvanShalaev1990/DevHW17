INSERT INTO users (USER_NAME) VALUES
('Test User');

INSERT INTO users (USER_NAME, PASSWORD, ROLE) VALUES
('user', '{noop}jdbcDefault', 'ADMIN');

INSERT INTO note (TITLE, CONTENT) VALUES
('First','First content from H2 DB'),
('Second','Second content from H2 DB'),
('Third','First content from H2 DB'),
('Forth','Forth content from H2 DB'),
('Fifth','Fifth content from H2 DB');