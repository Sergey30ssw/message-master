package com.messagemaster.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private Long userId;
}