package com.example.travelcommunity.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequestDto {
    private String username; // 사용자명
    private String email; // 이메일
    private String password; // 비밀번호
    private String phoneNumber; // 전화번호
    private Date dateOfBirth; // 생년월일
    private String gender; // 성별
    private String country; // 거주 국가
    private String preferredLanguage; // 선호 언어
    private String travelInterests; // 여행 관심사

}
