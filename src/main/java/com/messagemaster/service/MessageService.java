package com.messagemaster.service;

import com.messagemaster.dto.MessageDTO;
import java.util.List;

public interface MessageService {
    MessageDTO sendMessage(MessageDTO messageDTO);
    List<MessageDTO> getUserMessages(Long userId);
    List<MessageDTO> getConversation(Long userId1, Long userId2);
    void deleteMessage(Long messageId);
}