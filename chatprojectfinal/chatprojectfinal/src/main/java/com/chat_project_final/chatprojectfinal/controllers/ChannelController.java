package com.chat_project_final.chatprojectfinal.controllers;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import com.chat_project_final.chatprojectfinal.http.AppResponse;
import com.chat_project_final.chatprojectfinal.services.ChannelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChannelController {

        private final ChannelService channelService;

        public ChannelController(ChannelService channelService) {
            this.channelService = channelService;
        }

        @PostMapping("/channels")
        public ResponseEntity<?> createChannel(@RequestBody Channel channel) {
            boolean isCreated = channelService.createChannel(channel);

            if (isCreated) {
                return AppResponse.success()
                        .withMessage("Channel created successfully")
                        .build();
            }

            return AppResponse.error()
                    .withMessage("Channel could not be created")
                    .build();
        }


        @PostMapping("/channels/{channelId}/users/{userId}")
        public ResponseEntity<?> addUserToChannel(@PathVariable int channelId, @PathVariable int userId, @RequestParam String role) {
            boolean isAdded = channelService.addUserToChannel(channelId, userId, role);

            if (isAdded) {
                return AppResponse.success()
                        .withMessage("User added to channel successfully")
                        .build();
            }

            return AppResponse.error()
                    .withMessage("User could not be added to channel")
                    .build();
        }


        @PutMapping("/channels/{channelId}/users/{userId}/admin")
        public ResponseEntity<?> setAdminRole(@PathVariable int channelId, @PathVariable int userId) {
            boolean isAdminSet = channelService.setAdminRole(channelId, userId);

            if (isAdminSet) {
                return AppResponse.success()
                        .withMessage("User role set to Admin")
                        .build();
            }

            return AppResponse.error()
                    .withMessage("User could not be set as Admin")
                    .build();
        }

        @GetMapping("/channels/user/{userId}")
        public ResponseEntity<?> getAllChannelsByUser(@PathVariable int userId) {
            List<Channel> channels = channelService.getAllChannelsByUserId(userId);

            return AppResponse.success()
                    .withData(channels)
                    .build();
        }

        @DeleteMapping("/channels/{channelId}")
        public ResponseEntity<?> deleteChannel(@PathVariable int channelId) {
            boolean isDeleted = channelService.deleteChannel(channelId);

            if (!isDeleted) {
                return AppResponse.error()
                        .withMessage("Channel not found")
                        .build();
            }

            return AppResponse.success()
                    .withMessage("Channel deleted successfully")
                    .build();
        }

    @PutMapping("channels/{channelId}/name")
    public ResponseEntity<?> updateChannelName(@PathVariable int channelId, @RequestParam String newName) {
        boolean isUpdated = channelService.updateChannelName(channelId, newName);

        if (isUpdated) {
            return AppResponse.success()
                    .withMessage("Channel name updated successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Channel not found or name could not be updated")
                .build();
    }
}
