package com.chat_project_final.chatprojectfinal.services;

import com.chat_project_final.chatprojectfinal.entities.User;
import com.chat_project_final.chatprojectfinal.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
