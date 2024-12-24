package com.example.travelcommunity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String username; // username
    private String email; // email
    private String password; // password
    private String profileImage; // profile_image
    private String biography; // biography
    private String role; // role
    private String socialProvider; // social_provider
    private String socialId; // social_id
    private String phoneNumber; // phone_number
    private String emailVerified; // email_verified
    private LocalDate dateOfBirth; // date_of_birth
    private String gender; // gender
    private String country; // country
    private String preferredLanguage; // preferred_language
    private String travelInterests; // travel_interests
    private String grade; // grade
    private String profileVisibility; // profile_visibility
}
