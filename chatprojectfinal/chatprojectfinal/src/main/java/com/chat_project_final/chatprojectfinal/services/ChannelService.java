package com.chat_project_final.chatprojectfinal.services;

import com.chat_project_final.chatprojectfinal.entities.Channel;
import com.chat_project_final.chatprojectfinal.entities.User;
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

    public Channel getChannel(int id) {
        return channelRepository.fetch(id);
    }


    public boolean addUserToChannel(int channelId, int userId) {
        // Проверка дали потребителят не е вече в канала
        return this.channelRepository.addUserToChannel(channelId, userId);
    }


    public boolean deleteChannel(int channelId) {
        return this.channelRepository.delete(channelId);
    }




    public List<Channel> getAllChannelsByOwner(int userId) {
        return this.channelRepository.fetchAllByOwnerId(userId);
    }

    public List<Channel> getAllChannelsByMember(int userId) throws Exception {
        return this.channelRepository.fetchAllByMemberId(userId);
    }


    public boolean setAdminRole(int channelId, int userId) {
        return this.channelRepository.setAdminRole(channelId, userId);
    }

    public boolean setOwnerRole(int channelId, int userId) {
        return this.channelRepository.setOwnerRole(channelId, userId);
    }

    public boolean updateChannelName(Channel channel) {
        return this.channelRepository.update(channel);


    }




}
