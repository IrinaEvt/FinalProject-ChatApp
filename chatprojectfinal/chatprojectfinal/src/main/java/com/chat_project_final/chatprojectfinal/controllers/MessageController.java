package com.chat_project_final.chatprojectfinal.controllers;

import com.chat_project_final.chatprojectfinal.entities.Message;
import com.chat_project_final.chatprojectfinal.http.AppResponse;
import com.chat_project_final.chatprojectfinal.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        boolean isCreated = messageService.createMessage(message);

        if (isCreated) {
            return AppResponse.success()
                    .withMessage("Message created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Message could not be created")
                .build();
    }

    @PostMapping("/messages/user/{friendId}")
    public ResponseEntity<?> createPrivateMessage(@RequestBody Message message, @PathVariable int friendId) {
        boolean isCreated = messageService.createPrivateMessage(message, friendId);

        if (isCreated) {
            return AppResponse.success()
                    .withMessage("Private message sent successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Private message could not be sent")
                .build();
    }

    @GetMapping("/messages/channel/{channelId}")
    public ResponseEntity<?> getMessagesByChannel(@PathVariable int channelId) {
        ArrayList<Message> messages = (ArrayList<Message>) messageService.getMessagesByChannel(channelId);

        return AppResponse.success()
                .withData(messages)
                .build();
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> getMessage(@PathVariable int id) {
        Message message = messageService.getMessage(id);

        if (message == null) {
            return AppResponse.error()
                    .withMessage("Message not found")
                    .build();
        }

        return AppResponse.success()
                .withDataAsArray(message)
                .build();
    }

    @GetMapping("/messages/user/{userId}/friend/{friendId}")
    public ResponseEntity<?> getMessagesForUser(@PathVariable int userId,@PathVariable int friendId) {
        ArrayList<Message> messages = (ArrayList<Message>) messageService.getMessagesForUser(userId,friendId);

        return AppResponse.success()
                .withData(messages)
                .build();
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable int id) {
        boolean isDeleted = messageService.deleteMessage(id);

        if (!isDeleted) {
            return AppResponse.error()
                    .withMessage("Message not found")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Message deleted successfully")
                .build();
    }


}
