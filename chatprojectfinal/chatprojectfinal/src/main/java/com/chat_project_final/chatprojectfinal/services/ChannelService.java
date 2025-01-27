package com.chat_project_final.chatprojectfinal.services;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import com.chat_project_final.chatprojectfinal.repos.ChannelRepository;
import com.chat_project_final.chatprojectfinal.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;


    public ChannelService(ChannelRepository channelRepository, UserRepository userRepository) {
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }


    public boolean createChannel(Channel channel) {
        return this.channelRepository.insert(channel);
    }


    public boolean addUserToChannel(int channelId, int userId, String role) {
        // Проверка дали потребителят не е вече в канала
        return this.channelRepository.addUserToChannel(channelId, userId);
    }


    public boolean deleteChannel(int channelId) {
        return this.channelRepository.delete(channelId);
    }


    public List<Channel> getAllChannelsByUserId(int userId) {
        return this.channelRepository.fetchAllByUserId(userId);
    }


    public boolean setAdminRole(int channelId, int userId) {
        return this.channelRepository.setAdminRole(channelId, userId);
    }

    public boolean updateChannelName(int channelId, String newName) {
        Channel channel = this.channelRepository.fetch(channelId);

        if (channel == null) {
            return false; // Каналът не съществува
        }

        channel.setName(newName); // Променяме името на канала
        return this.channelRepository.update(channel); // Актуализираме канала в базата данни
    }




}
