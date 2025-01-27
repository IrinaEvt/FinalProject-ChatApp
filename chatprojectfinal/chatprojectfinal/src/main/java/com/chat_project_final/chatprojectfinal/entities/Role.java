package com.chat_project_final.chatprojectfinal.entities;

public class Role {
    public static final String TABLE = "tc_channel_user";

    public static class columns {
        public static final String CHANNEL_ID = "channel_id";
        public static final String USER_ID = "user_id";
        public static final String ROLE = "role";
    }

    private int channelId;
    private int userId;
    private String role;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

