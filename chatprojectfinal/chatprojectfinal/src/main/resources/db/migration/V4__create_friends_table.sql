
CREATE TABLE IF NOT EXISTS td_friends (
    id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    PRIMARY KEY (user_id, friend_id)
);

INSERT INTO td_friends (user_id, friend_id)
VALUES
('1', '2'),
('1', '3'),
('2', '3');