package com.chat_project_final.chatprojectfinal.mappers;

import com.chat_project_final.chatprojectfinal.entities.ChannelUser;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<ChannelUser> {
    @Override
    public ChannelUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChannelUser channelUser = new ChannelUser();
        channelUser.setChannelId(rs.getInt(ChannelUser.columns.CHANNEL_ID));
        channelUser.setUserId(rs.getInt(ChannelUser.columns.USER_ID));
        channelUser.setRole(rs.getString(ChannelUser.columns.ROLE));

        return channelUser;
    }
}
