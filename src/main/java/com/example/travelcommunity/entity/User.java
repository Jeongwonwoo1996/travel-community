package com.example.travelcommunity.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
public class User {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String country;
    private String preferredLanguage;
    private String travelInterests;
    private String profileImage;
    private String biography;
    private String role;
    private String grade;
    private String profileVisibility;
    private Timestamp lastLogin;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
