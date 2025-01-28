package com.chat_project_final.chatprojectfinal.mappers;

import com.chat_project_final.chatprojectfinal.entities.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(rs.getInt(Message.columns.ID));
        message.setChannelId(rs.getInt(Message.columns.CHANNEL_ID));
        message.setSenderId(rs.getInt(Message.columns.SENDER_ID));
        message.setReceiverId(rs.getInt(Message.columns.RECEIVER_ID));
        message.setContent(rs.getString(Message.columns.CONTENT));
        message.setTimestamp(rs.getTimestamp(Message.columns.TIMESTAMP));

        return message;
    }
}
