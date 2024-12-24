package com.example.travelcommunity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String country;
    private String preferredLanguage;
    private String travelInterests;

}
