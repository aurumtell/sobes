package com.chat.service;

import com.chat.ChatService;
import com.chat.model.entity.ChatEntity;
import com.chat.model.entity.MessageEntity;
import com.chat.model.request.Message;
import com.chat.model.response.MessageResponse;
import com.chat.repo.ChatRepository;
import com.chat.repo.MessageRepository;
import com.chat.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public MessageResponse sendMessage(Long chatId, Message message) {
        MessageEntity messageEntity = new MessageEntity();
        if (!chatRepository.existsById(chatId)) {
            messageEntity.setChat(chatRepository.findById(chatId).get());
        } else {
            ChatEntity chat = new ChatEntity();

        }


        messageEntity.setDate(LocalDateTime.now());
        messageEntity.setContent(message.getText());
        messageEntity.setSender(userRepository.findById(message.getSender()).get());
        messageEntity.setIsRead(false);
        messageRepository.save(messageEntity);
        return new MessageResponse(messageEntity);
    }

    public void markMessagesAsRead(Long messageId) {
        messageRepository.findById(messageId).get().setIsRead(true);
    }
}
