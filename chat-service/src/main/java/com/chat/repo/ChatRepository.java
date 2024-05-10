package com.chat.repo;

import com.chat.model.entity.ChatEntity;
import com.chat.model.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
}
