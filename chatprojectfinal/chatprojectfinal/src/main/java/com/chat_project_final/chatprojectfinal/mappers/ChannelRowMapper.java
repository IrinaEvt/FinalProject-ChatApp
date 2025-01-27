package com.chat_project_final.chatprojectfinal.mappers;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChannelRowMapper implements RowMapper<Channel> {

        @Override
        public Channel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Channel channel = new Channel();
            channel.setId(rs.getInt(Channel.columns.ID));
            channel.setName(rs.getString(Channel.columns.NAME));
            channel.setOwnerId(rs.getInt(Channel.columns.OWNER_ID));
            return channel;
        }
}
