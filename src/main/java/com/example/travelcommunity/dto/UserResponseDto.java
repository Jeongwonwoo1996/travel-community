package com.example.travelcommunity.dto;



import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String profileImage;
    private String biography;
    private String role;
    private String grade;
    private String profileVisibility;
    private Date dateOfBirth;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
