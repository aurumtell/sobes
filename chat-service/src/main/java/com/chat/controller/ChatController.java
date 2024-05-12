package com.chat.controller;

import com.chat.model.entity.ChatEntity;
import com.chat.model.request.Message;
import com.chat.model.response.MessageResponse;
import com.chat.security.services.UserDetailsImpl;

import com.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;  // Сервис для работы с сообщениями

//    @MessageMapping("/chat")
//    @SendTo("/topic/messages/{chatId}")
//    public MessageResponse sendMessage(@DestinationVariable Long chatId, Message message) {
//        return messageService.sendMessage(chatId, message);
//    }
//
//    @MessageMapping("/chat/read")
//    public void markMessagesAsRead(@Payload Long messageId) {
//        messageService.markMessagesAsRead(messageId);
//    }

    @PostMapping(value = "/chat/{userId}")
    public ChatEntity createChat(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl user) {
        return messageService.createChat(userId, user);
    }

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public MessageResponse sendMessage(Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("chat/{chatId}/messages")
    public List<MessageResponse> getMessages(@PathVariable Long chatId) {
        return messageService.getMessages(chatId);
    }
}
