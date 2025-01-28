CREATE TABLE IF NOT EXISTS td_messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    channel_id INT,
    sender_id INT NOT NULL,
    receiver_id INT,
    content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active INT DEFAULT 1
);

CREATE TABLE  IF NOT EXISTS tc_message_channel (
    message_id INT NOT NULL,
    channel_id INT NOT NULL,
    PRIMARY KEY (message_id, channel_id)
);
