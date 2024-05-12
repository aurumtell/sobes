package com.chat.model.response;

import com.chat.model.entity.MessageEntity;
import com.chat.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResponse {
    private Long chatId;
    private LocalDateTime date;
    private UserEntity sender;
    private String text;

    public MessageResponse(MessageEntity message) {
        this.chatId = message.getChat().getId();
        this.sender = message.getSender();
        this.date = message.getDate();
        this.text = message.getContent();
    }
}
