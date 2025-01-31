package com.chat_project_final.chatprojectfinal.repos;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import com.chat_project_final.chatprojectfinal.entities.User;
import com.chat_project_final.chatprojectfinal.mappers.UserRowMapper;
import com.chat_project_final.chatprojectfinal.system.db.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final QueryBuilder<User> db;

    public UserRepository(QueryBuilder<User> db) {
        this.db = db;
    }

    public boolean insert(User user) {
        return this.db.into(User.TABLE)
                .withValue(User.columns.USERNAME, user.getUsername())
                .withValue(User.columns.PASSWORD, user.getPassword())
                .insert();
    }

    public User fetch(int id) {
        return this.db.selectAll()
                .from(User.TABLE)
                .where(User.columns.ID, id)
                .andWhere(User.columns.IS_ACTIVE,1)
                .fetch(new UserRowMapper());
    }

    public List<User> fetchAll() {
        return this.db.selectAll()
                .from(User.TABLE)
                .where(User.columns.IS_ACTIVE,1)
                .fetchAll(new UserRowMapper());
    }

    public boolean delete(int id) {
        int resultCount = this.db.updateTable(User.TABLE)
                .set(User.columns.IS_ACTIVE, 0)
                .where(User.columns.ID, id)
                .update();

        return resultCount == 1;
    }
}
