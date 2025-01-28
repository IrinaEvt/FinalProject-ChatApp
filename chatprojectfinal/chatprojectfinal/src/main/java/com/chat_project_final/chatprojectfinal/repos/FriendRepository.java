package com.chat_project_final.chatprojectfinal.repos;

import com.chat_project_final.chatprojectfinal.entities.Friend;
import com.chat_project_final.chatprojectfinal.system.db.QueryProcessor;
import com.chat_project_final.chatprojectfinal.system.db.operations.InsertQueryBuilder;
import com.chat_project_final.chatprojectfinal.system.db.operations.SelectQueryBuilder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepository {
    private QueryProcessor<Friend> queryProcessor;

    public FriendRepository(QueryProcessor<Friend> queryProcessor) {
        this.queryProcessor = queryProcessor;
    }

    public boolean insert(Friend friend) {
        return new InsertQueryBuilder(queryProcessor, Friend.TABLE)
                .withValue(Friend.columns.USER_ID, friend.getUserId())
                .withValue(Friend.columns.FRIEND_ID, friend.getFriendId())
                .insert();
    }

    public List<Friend> fetchFriendsByUserId(int userId) {
        return new SelectQueryBuilder<>(queryProcessor, "*")
                .from(Friend.TABLE)
                .where(Friend.columns.USER_ID, userId)
                .fetchAll(Friend.ROW_MAPPER);
    }
}
