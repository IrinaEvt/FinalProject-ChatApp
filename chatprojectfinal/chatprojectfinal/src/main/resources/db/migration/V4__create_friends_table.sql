CREATE TABLE IF NOT EXISTS td_friends (
    id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    PRIMARY KEY (user_id, friend_id)
);