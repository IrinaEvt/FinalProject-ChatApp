CREATE TABLE IF NOT EXISTS td_messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    channel_id INT NOT NULL,
    sender_id INT NOT NULL,
    content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE  IF NOT EXISTS tc_message_channel (
    message_id INT NOT NULL,
    channel_id INT NOT NULL,
    PRIMARY KEY (message_id, channel_id)
);
