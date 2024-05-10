package com.chat.controller;

import com.chat.model.request.Message;
import com.chat.model.response.MessageResponse;
import com.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;  // Сервис для работы с сообщениями

    @MessageMapping("/chat")
    @SendTo("/topic/messages/{chatId}")
    public MessageResponse sendMessage(@DestinationVariable Long chatId, Message message) {
        return messageService.sendMessage(chatId, message);
    }

    @MessageMapping("/chat/read")
    public void markMessagesAsRead(@Payload Long messageId) {
        messageService.markMessagesAsRead(messageId);
    }
}
