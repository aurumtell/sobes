package com.chat.service;

import com.chat.exception.BusinessException;
import com.chat.model.entity.ChatEntity;
import com.chat.model.entity.MessageEntity;
import com.chat.model.entity.UserEntity;
import com.chat.model.request.Message;
import com.chat.model.response.MessageResponse;
import com.chat.repo.ChatRepository;
import com.chat.repo.MessageRepository;
import com.chat.repo.UserRepository;
import com.chat.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;


    public ChatEntity createChat(Long userId, UserDetailsImpl user) {
        System.out.println("create chat service");
        ChatEntity chat = new ChatEntity();
        UserEntity participantOne = userRepository.findById(user.getId()).get();
        System.out.println(user.getId());
        System.out.println(participantOne);
        UserEntity participantTwo = userRepository.findById(userId).get();
        if (chatRepository.existsByParticipantoneAndParticipanttwo(participantOne, participantTwo)) {
            throw new BusinessException("Chat already exists!");
        }
        chat.setParticipantone(participantOne);
        chat.setParticipanttwo(participantTwo);
        System.out.println("chat save");
        return chatRepository.save(chat);
    }

    public MessageResponse sendMessage(Message message) {
        Optional<UserEntity> sender = userRepository.findById(message.getSenderId());
        Optional<ChatEntity> chat = chatRepository.findById(message.getChatId());
        if (chat.isEmpty() || sender.isEmpty()) {
            throw new IllegalArgumentException("Invalid chat or sender");
        }
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setChat(chat.get());
        messageEntity.setSender(sender.get());
        messageEntity.setContent(message.getText());
        messageEntity.setDate(LocalDateTime.now());
        MessageEntity savedMessage = messageRepository.save(messageEntity);
        return new MessageResponse(savedMessage);
    }

    public List<MessageResponse> getMessages(Long chatId) {
        return messageRepository.findByChat(chatRepository.findById(chatId).get()).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MessageResponse toResponse(MessageEntity message) {
        return new MessageResponse(message);
    }

    public List<ChatEntity> getChats(UserDetailsImpl user) {
        return chatRepository.findByParticipantoneOrParticipanttwo(userRepository.findById(user.getId()).get(), userRepository.findById(user.getId()).get());
    }
}

