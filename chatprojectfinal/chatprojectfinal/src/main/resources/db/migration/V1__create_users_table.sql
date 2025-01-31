
CREATE TABLE IF NOT EXISTS td_users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    is_active INT DEFAULT 1
);

INSERT INTO td_users (username, password, is_active)
VALUES
('irinaEvt', 'pass1', '1'),
('Pesho', 'pass2', '1'),
('Tosho', 'pass1', '1');