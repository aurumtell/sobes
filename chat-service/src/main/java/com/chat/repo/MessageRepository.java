package com.chat.repo;

import com.chat.model.entity.MessageEntity;
import com.chat.model.request.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
