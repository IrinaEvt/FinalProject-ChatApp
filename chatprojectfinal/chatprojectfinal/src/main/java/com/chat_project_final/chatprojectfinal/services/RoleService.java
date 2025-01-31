package com.chat_project_final.chatprojectfinal.services;

import com.chat_project_final.chatprojectfinal.entities.ChannelUser;
import com.chat_project_final.chatprojectfinal.entities.Message;
import com.chat_project_final.chatprojectfinal.repos.MessageRepository;
import com.chat_project_final.chatprojectfinal.repos.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public String getRole(int channelId, int userId) throws Exception {
        ChannelUser user = this.roleRepository.fetchUserRole(channelId,userId);
        System.out.println(user.getRole());
        return user.getRole();
    }

    public boolean isMember(int channelId,int userId) throws Exception {
        return this.getRole(channelId, userId).equals("member");
    }
    public boolean isAdmin(int channelId,int userId) throws Exception {
        return this.getRole(channelId, userId).equals("admin");
    }
    public boolean isOwner(int channelId,int userId) throws Exception {
        return this.getRole(channelId, userId).equals("owner");
    }

}
