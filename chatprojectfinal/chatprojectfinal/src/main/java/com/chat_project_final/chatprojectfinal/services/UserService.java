package com.chat_project_final.chatprojectfinal.services;

import com.chat_project_final.chatprojectfinal.entities.Friend;
import com.chat_project_final.chatprojectfinal.entities.User;
import com.chat_project_final.chatprojectfinal.repos.FriendRepository;
import com.chat_project_final.chatprojectfinal.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    public UserService(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    public boolean createUser(User user) {
        return this.userRepository.insert(user);
    }

    public User getUser(int id) {
        return userRepository.fetch(id);
    }

    public List<User> getAllUsers() {
        return userRepository.fetchAll();
    }

    public boolean removeUser(int id) {
        return this.userRepository.delete(id);
    }

    public boolean addFriend(int userId, int friendId) {
        Friend friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        return friendRepository.insert(friend);
    }

    public List<Friend> getFriendsByUserId(int userId) {

        return friendRepository.fetchFriendsByUserId(userId);

    }
}
