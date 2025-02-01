
CREATE TABLE IF NOT EXISTS td_channels (
    id BIGINT  PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    owner_id BIGINT NOT NULL,
    is_active INT DEFAULT 1
);

INSERT INTO td_channels (name, owner_id, is_active)
VALUES
('Irka Channel', '1', '1'),
('Channel for all', '1', '1'),
('Tosho and friends', '3', '1');



CREATE TABLE IF NOT EXISTS tc_channel_user (
    channel_id INT NOT NULL,
    user_id INT NOT NULL,
    role VARCHAR(50) DEFAULT 'member',
    PRIMARY KEY(channel_id, user_id)
);


