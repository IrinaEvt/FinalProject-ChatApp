package com.chat_project_final.chatprojectfinal.services;

import com.chat_project_final.chatprojectfinal.entities.Message;
import com.chat_project_final.chatprojectfinal.repos.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;



    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;

    }


    public boolean createMessage(Message message) {
        return this.messageRepository.insert(message);
    }

    public boolean createPrivateMessage(Message message, int friendId) {
        return this.messageRepository.insertPrivateMessage(message, friendId);
    }

    public Message getMessage(int id) {
        return this.messageRepository.fetch(id);
    }


    public List<Message> getMessagesByChannel(int channelId) {
        return this.messageRepository.fetchAllByChannel(channelId);
    }

    public List<Message> getMessagesForUser(int userId, int friendId) {
        return this.messageRepository.fetchAllByUser(userId, friendId);
    }

    public boolean deleteMessage(int id) {
        return this.messageRepository.delete(id);
    }
}
