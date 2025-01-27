package com.chat_project_final.chatprojectfinal.mappers;

import com.chat_project_final.chatprojectfinal.entities.User;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(User.columns.ID));
        user.setUsername(rs.getString(User.columns.USERNAME));
        user.setPassword(rs.getString(User.columns.PASSWORD));
        return user;
    }
}
