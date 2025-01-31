package com.chat_project_final.chatprojectfinal.repos;

import com.chat_project_final.chatprojectfinal.entities.ChannelUser;
import com.chat_project_final.chatprojectfinal.entities.Message;
import com.chat_project_final.chatprojectfinal.mappers.MessageRowMapper;
import com.chat_project_final.chatprojectfinal.mappers.RoleRowMapper;
import com.chat_project_final.chatprojectfinal.system.db.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {
    private final QueryBuilder<ChannelUser> db;

    public RoleRepository(QueryBuilder<ChannelUser> db) {
        this.db = db;
    }

    public ChannelUser fetchUserRole(int channelId, int userId) throws Exception {
        return this.db.select(ChannelUser.columns.ROLE)
                .from(ChannelUser.TABLE)
                .where(ChannelUser.columns.CHANNEL_ID, channelId)
                .andWhere(ChannelUser.columns.USER_ID, userId)
                .fetch(new RoleRowMapper());
    }
}
