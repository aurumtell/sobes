package com.chat.controller;

import com.chat.model.entity.ChatEntity;
import com.chat.model.response.MessageResponse;
import com.chat.security.services.UserDetailsImpl;
import com.chat.service.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Authorization")
public class ChatController {

    @Autowired
    MessageService messageService;  // Сервис для работы с сообщениями

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
    @ResponseBody
    public ChatEntity createChat(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl user) {
        System.out.println("chat");
        return messageService.createChat(userId, user);
    }

    @GetMapping("/chat/{chatId}/messages")
    public List<MessageResponse> getMessages(@PathVariable Long chatId) {
        return messageService.getMessages(chatId);
    }

    @GetMapping("/chat")
    public List<ChatEntity> getChats(@AuthenticationPrincipal UserDetailsImpl user) {
        return messageService.getChats(user);
    }
}
