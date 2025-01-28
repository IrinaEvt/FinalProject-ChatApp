package com.chat_project_final.chatprojectfinal.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Friend {
    public static final String TABLE = "td_friends";

    public static class columns {
        public static final String ID = "id";
        public static final String USER_ID = "user_id";
        public static final String FRIEND_ID = "friend_id";
    }

    private int id;
    private int userId;
    private int friendId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public static final RowMapper<Friend> ROW_MAPPER = new RowMapper<Friend>() {
        @Override
        public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
            Friend friend = new Friend();
            friend.setId(rs.getInt(columns.ID));
            friend.setUserId(rs.getInt(columns.USER_ID));
            friend.setFriendId(rs.getInt(columns.FRIEND_ID));
            return friend;
        }
    };

}
