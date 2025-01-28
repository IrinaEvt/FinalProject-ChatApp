package com.chat_project_final.chatprojectfinal.repos;

import com.chat_project_final.chatprojectfinal.entities.Message;
import com.chat_project_final.chatprojectfinal.mappers.MessageRowMapper;
import com.chat_project_final.chatprojectfinal.system.db.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepository {
    private final QueryBuilder<Message> db;

    public MessageRepository(QueryBuilder<Message> db) {
        this.db = db;
    }

    public boolean insert(Message message) {
        return this.db.into(Message.TABLE)
                .withValue(Message.columns.SENDER_ID, message.getSenderId())
                .withValue(Message.columns.CONTENT, message.getContent())
                .insert();
    }

    public boolean insertPrivateMessage(Message message) {
        return this.db.into(Message.TABLE)
                .withValue(Message.columns.SENDER_ID, message.getSenderId())
                .withValue(Message.columns.RECEIVER_ID, message.getReceiverId())
                .withValue(Message.columns.CONTENT, message.getContent())
                .insert();
    }

    public Message fetch(int id) {
        return this.db.selectAll()
                .from(Message.TABLE)
                .where(Message.columns.ID, id)
                .fetch(new MessageRowMapper());
    }

    public List<Message> fetchAllByChannel(int channelId) {
        return this.db.selectAll()
                .from(Message.TABLE)
                .where(Message.columns.CHANNEL_ID, channelId)
                .fetchAll(new MessageRowMapper());
    }

    public List<Message> fetchAllByUser(int userId) {
        return this.db.selectAll()
                .from(Message.TABLE)
                .where(Message.columns.RECEIVER_ID, userId)
                .orWhere(Message.columns.SENDER_ID, userId)
                .fetchAll(new MessageRowMapper());
    }

    public boolean delete(int id) {
        int resultCount = this.db.updateTable(Message.TABLE)
                .set(Message.columns.IS_ACTIVE, 1)
                .where(Message.columns.ID, id)
                .update();

        return resultCount == 1;
    }
}
