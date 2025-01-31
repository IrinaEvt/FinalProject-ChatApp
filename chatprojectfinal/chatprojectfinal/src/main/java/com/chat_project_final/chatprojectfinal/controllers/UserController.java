package com.chat_project_final.chatprojectfinal.controllers;

import com.chat_project_final.chatprojectfinal.entities.Friend;
import com.chat_project_final.chatprojectfinal.entities.User;
import com.chat_project_final.chatprojectfinal.http.AppResponse;
import com.chat_project_final.chatprojectfinal.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        boolean isCreated = userService.createUser(user);

        if (isCreated) {
            return AppResponse.success()
                    .withMessage("User created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("User could not be created")
                .build();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return AppResponse.success()
                .withData(users)
                .build();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        User user = userService.getUser(id);

        if (user == null) {
            return AppResponse.error()
                    .withMessage("User not found")
                    .build();
        }

        return AppResponse.success()
                .withDataAsArray(user)
                .build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.removeUser(id);

        if (!isDeleted) {
            return AppResponse.error()
                    .withMessage("User not found")
                    .build();
        }

        return AppResponse.success()
                .withMessage("User deleted successfully")
                .build();
    }

    @PostMapping("/users/friends")
    public String addFriend(@RequestBody Friend friend) {
        boolean isAdded = userService.addFriend(friend.getUserId(), friend.getFriendId());
        if (isAdded) {
            return "Friend added successfully!";
        }
        return "Failed to add friend.";
    }

    @GetMapping("users/{userId}/friends")
    public ResponseEntity<?> getFriendsByUserId(@PathVariable int userId) {
        System.out.println(userId);
        List<Friend> friends = userService.getFriendsByUserId(userId);



        return AppResponse.success()
                .withData(friends)
                .build();
    }
}
