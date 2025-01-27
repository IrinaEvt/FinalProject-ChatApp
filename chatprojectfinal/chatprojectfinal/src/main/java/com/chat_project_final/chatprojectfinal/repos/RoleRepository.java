package com.chat_project_final.chatprojectfinal.repos;

import com.chat_project_final.chatprojectfinal.entities.Role;
import com.chat_project_final.chatprojectfinal.system.db.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public class RoleRepository {
        private final QueryBuilder<Role> db;

        public RoleRepository(QueryBuilder<Role> db) {
            this.db = db;
        }

        public boolean assignRole(Role role) {
            return this.db.into(Role.TABLE)
                    .withValue(Role.columns.CHANNEL_ID, role.getChannelId())
                    .withValue(Role.columns.USER_ID, role.getUserId())
                    .withValue(Role.columns.ROLE, role.getRole())
                    .insert();
        }



        public boolean updateRole(int channelId, int userId, String newRole) {
            int resultCount = this.db.updateTable(Role.TABLE)
                    .set(Role.columns.ROLE, newRole)
                    .where(Role.columns.CHANNEL_ID, channelId)
                 //   .and(Role.columns.USER_ID, userId)
                    .andWhere(Role.columns.USER_ID, userId)
                    .update();
            return resultCount ==1;
        }

        public boolean removeUserFromChannel(int channelId, int userId) {
            int resultCount = this.db.deleteTable(Role.TABLE)
                    .where(Role.columns.CHANNEL_ID, channelId)
                    .andWhere(Role.columns.USER_ID, userId)
                    .delete();
            return resultCount == 1;
        }
}
