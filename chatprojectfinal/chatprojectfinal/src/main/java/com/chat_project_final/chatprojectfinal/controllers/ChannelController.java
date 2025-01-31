package com.chat_project_final.chatprojectfinal.controllers;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import com.chat_project_final.chatprojectfinal.entities.User;
import com.chat_project_final.chatprojectfinal.http.AppResponse;
import com.chat_project_final.chatprojectfinal.services.ChannelService;
import com.chat_project_final.chatprojectfinal.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

@RestController
public class ChannelController {

        private final ChannelService channelService;
         private final RoleService roleService;

        public ChannelController(ChannelService channelService, RoleService roleService) {
            this.channelService = channelService;
            this.roleService = roleService;
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

    @GetMapping("/channels/{id}")
    public ResponseEntity<?> getSingleChannel(@PathVariable int id) {
        Channel channel = channelService.getChannel(id);

        if (channel == null) {
            return AppResponse.error()
                    .withMessage("User not found")
                    .build();
        }

        return AppResponse.success()
                .withDataAsArray(channel)
                .build();
    }


        @PostMapping("/channels/{channelId}/users/{userId}")
        public ResponseEntity<?> addUserToChannel(@PathVariable int channelId, @PathVariable int userId) {
            boolean isAdded = channelService.addUserToChannel(channelId, userId);

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

    @PutMapping("/channels/{channelId}/users/{userId}/owner")
    public ResponseEntity<?> setOwnerRole(@PathVariable int channelId, @PathVariable int userId) {
        boolean isOwnerSet = channelService.setOwnerRole(channelId, userId);

        if (isOwnerSet) {
            return AppResponse.success()
                    .withMessage("User role set to owner")
                    .build();
        }

        return AppResponse.error()
                .withMessage("User could not be set as owner")
                .build();
    }

        @GetMapping("/channels/owner/{userId}")
        public ResponseEntity<?> getAllChannelsByOwner(@PathVariable int userId) {
            List<Channel> channels = channelService.getAllChannelsByOwner(userId);

            return AppResponse.success()
                    .withData(channels)
                    .build();
        }

    @GetMapping("/channels/member/{userId}")
    public ResponseEntity<?> getAllChannelsByMember(@PathVariable int userId) throws Exception {
        List<Channel> channels = channelService.getAllChannelsByMember(userId);

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

    @PutMapping("/channels")
    public ResponseEntity<?> updateChannelName(@RequestBody Channel channel) {
        boolean isUpdated = channelService.updateChannelName(channel);

        if (isUpdated) {
            return AppResponse.success()
                    .withMessage("Channel name updated successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Channel not found or name could not be updated")
                .build();
    }

    @GetMapping("/channels/{channelId}/role/{userId}")
    public String getRole(@PathVariable int channelId, @PathVariable int userId) throws Exception {
           return roleService.getRole(channelId, userId);
    }
}
