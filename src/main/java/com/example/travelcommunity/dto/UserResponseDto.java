package com.example.travelcommunity.dto;



import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

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
    private LocalDate dateOfBirth;
    private Timestamp lastLogin;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
