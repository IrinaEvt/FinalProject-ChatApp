package com.chat_project_final.chatprojectfinal.repos;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import com.chat_project_final.chatprojectfinal.entities.ChannelUser;
import com.chat_project_final.chatprojectfinal.mappers.ChannelRowMapper;
import com.chat_project_final.chatprojectfinal.system.db.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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


    public boolean delete(int id) {
        int resultCount = this.db.updateTable(Channel.TABLE)
                .set(Channel.columns.IS_ACTIVE, 1)
                .where(Channel.columns.ID, id)
                .update();

        return resultCount == 1;
    }

    public List<Channel> fetchAllByOwnerId(int userId) {
        return this.db.selectAll()
                .from(Channel.TABLE)
                .where(Channel.columns.OWNER_ID, userId)
                .fetchAll(new ChannelRowMapper());
    }

    public List<Channel> fetchAllByMemberId(int userId) {
        return this.db.select("c.id", "c.name", "c.owner_id")
                .from(ChannelUser.TABLE + " cu")              // Channel as 'c', ChannelUser as 'cu'
                .join(Channel.TABLE + " c", "cu.channel_id = c.id")  // Join Channel on channel_id
                .where("cu." + ChannelUser.columns.USER_ID, userId)
                .rawAndWhere("(cu.role = ? OR cu.role = ?)", "member", "admin")
                .fetchAll(new ChannelRowMapper());
    }

    public boolean addUserToChannel(int channelId, int userId) {

        return this.db.into(ChannelUser.TABLE)
                .withValue(ChannelUser.columns.CHANNEL_ID, channelId)
                .withValue(ChannelUser.columns.USER_ID, userId)
                .withValue(ChannelUser.columns.ROLE, "member")
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
