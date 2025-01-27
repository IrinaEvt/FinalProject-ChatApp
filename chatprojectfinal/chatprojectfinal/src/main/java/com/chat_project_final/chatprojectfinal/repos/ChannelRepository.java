package com.chat_project_final.chatprojectfinal.repos;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import com.chat_project_final.chatprojectfinal.entities.Role;
import com.chat_project_final.chatprojectfinal.mappers.ChannelRowMapper;
import com.chat_project_final.chatprojectfinal.system.db.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChannelRepository {
    private final QueryBuilder<Channel> db;

    public ChannelRepository(QueryBuilder<Channel> db) {
        this.db = db;
    }

    public boolean insert(Channel channel) {
        return this.db.into(Channel.TABLE)
                .withValue(Channel.columns.NAME, channel.getName())
                .withValue(Channel.columns.OWNER_ID, channel.getOwnerId())
                .insert();
    }

    public Channel fetch(int id) {
        return this.db.selectAll()
                .from(Channel.TABLE)
                .where(Channel.columns.ID, id)
                .fetch(new ChannelRowMapper());
    }

    public List<Channel> fetchAll() {
        return this.db.selectAll()
                .from(Channel.TABLE)
                .fetchAll(new ChannelRowMapper());
    }

    public boolean delete(int id) {
        int resultCount = this.db.deleteTable(Channel.TABLE)
                .where(Channel.columns.ID, id)
                .delete();
        return resultCount == 1;
    }



    public List<Channel> fetchAllByUserId(int userId) {
        return this.db.selectAll()
                .from(Channel.TABLE + " c")
                .join(Role.TABLE + " r", "c." + Channel.columns.ID + " = r." + Role.columns.CHANNEL_ID)
                .where("r." + Role.columns.USER_ID, userId)
                .fetchAll(new ChannelRowMapper());
    }
}
