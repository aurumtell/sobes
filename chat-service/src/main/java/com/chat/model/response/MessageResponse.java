package com.chat.model.response;

import com.chat.model.entity.MessageEntity;
import com.chat.model.entity.UserEntity;
import com.chat.model.request.Message;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Getter
@Setter
public class MessageResponse {
    private LocalDateTime date;
    private UserEntity sender;
    private String text;
    private Boolean isRead;

    public MessageResponse(MessageEntity message) {
        this.sender = message.getSender();
        this.date = message.getDate();
        this.text = message.getContent();
        this.isRead = message.getIsRead();
    }
}
