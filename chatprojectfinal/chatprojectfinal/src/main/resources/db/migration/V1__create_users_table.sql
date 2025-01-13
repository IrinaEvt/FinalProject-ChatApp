CREATE TABLE IF NOT EXISTS td_users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    is_active INT DEFAULT 1
);

INSERT INTO td_users (username, password, is_active)
VALUES
('firstUser', '1234', '1'),
('irinaEvt', '5678', '1');