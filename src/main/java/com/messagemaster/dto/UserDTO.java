package com.messagemaster.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String phoneNumber;
    private LocalDate birthDate;
    private String profileImagePath;
}