package com.chat_project_final.chatprojectfinal.entities;

import java.sql.Timestamp;

public class Message {
    public static final String TABLE = "td_messages";

    public static class columns {
        public static final String ID = "id";
        public static final String CHANNEL_ID = "channel_id";
        public static final String SENDER_ID = "sender_id";
        public static final String RECEIVER_ID = "receiver_id";
        public static final String CONTENT = "content";
        public static final String TIMESTAMP = "timestamp";
        public static final String IS_ACTIVE = "is_active";
    }

    private int id;
    private int senderId;
    private String content;
    private Timestamp timestamp;
    private int channelId;

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    private int receiverId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
