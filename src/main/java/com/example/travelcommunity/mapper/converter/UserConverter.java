package com.example.travelcommunity.mapper.converter;

import com.example.travelcommunity.dto.UserRequestDto;
import com.example.travelcommunity.dto.UserResponseDto;
import com.example.travelcommunity.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    // Request DTO → Entity 변환
    public User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());
        user.setCountry(dto.getCountry());
        user.setPreferredLanguage(dto.getPreferredLanguage());
        user.setTravelInterests(dto.getTravelInterests());
        return user;
    }

    // Entity → Response DTO 변환
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setProfileImage(user.getProfileImage());
        dto.setBiography(user.getBiography());
        dto.setRole(user.getRole());
        dto.setGrade(user.getGrade());
        dto.setProfileVisibility(user.getProfileVisibility());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setLastLogin(user.getLastLogin());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
