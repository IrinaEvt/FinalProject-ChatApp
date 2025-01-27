package com.chat_project_final.chatprojectfinal.repos;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import com.chat_project_final.chatprojectfinal.entities.ChannelUser;
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
        int resultCount = this.db.updateTable(Channel.TABLE)
                .set(Channel.columns.IS_ACTIVE, 1)
                .where(Channel.columns.ID, id)
                .update();

        return resultCount == 1;
    }


    public List<Channel> fetchAllByUserId(int userId) {
        return this.db.selectAll()
                .from(Channel.TABLE + " c")
                .join(ChannelUser.TABLE + " r", "c." + Channel.columns.ID + " = r." + ChannelUser.columns.CHANNEL_ID)
                .where("r." + ChannelUser.columns.USER_ID, userId)
                .fetchAll(new ChannelRowMapper());
    }

    public boolean addUserToChannel(int channelId, int userId) {


        ChannelUser newChannelUser = new ChannelUser();
        newChannelUser.setChannelId(channelId);
        newChannelUser.setUserId(userId);
        newChannelUser.setRole("member");


        return this.db.into(ChannelUser.TABLE)
                .withValue(ChannelUser.columns.CHANNEL_ID, newChannelUser.getChannelId())
                .withValue(ChannelUser.columns.USER_ID, newChannelUser.getUserId())
                .withValue(ChannelUser.columns.ROLE, newChannelUser.getRole())
                .insert();
    }

    public boolean setAdminRole(int channelId, int userId) {


        return this.db.updateTable(ChannelUser.TABLE)
                .set(ChannelUser.columns.ROLE, "admin")
                .where(ChannelUser.columns.CHANNEL_ID, channelId)
                .andWhere(ChannelUser.columns.USER_ID, userId)
                .update() == 1;
    }

    public boolean update(Channel channel) {
        int resultCount = this.db.updateTable(Channel.TABLE)
                .set(Channel.columns.NAME, channel.getName())  
                .where(Channel.columns.ID, channel.getId())
                .update();
        return resultCount == 1;

    }
}
