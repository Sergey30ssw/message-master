package com.messagemaster.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class MessageDTO {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private Long senderId;
    private Long receiverId;
}