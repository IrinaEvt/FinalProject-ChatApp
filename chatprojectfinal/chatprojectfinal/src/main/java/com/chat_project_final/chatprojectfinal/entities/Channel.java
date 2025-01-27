package com.chat_project_final.chatprojectfinal.entities;

public class Channel {
    public static final String TABLE = "td_channels";

    public static class columns {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String OWNER_ID = "owner_id";
        public static final String IS_ACTIVE = "is_active";
    }

    private int id;
    private String name;
    private int ownerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
