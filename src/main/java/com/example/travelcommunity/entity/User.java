package com.example.travelcommunity.entity;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class User {
    private Long userId; // 회원 고유 ID
    private String username; // 사용자명 (닉네임)
    private String email; // 이메일
    private String password; // 비밀번호
    private String profileImage; // 프로필 이미지 경로
    private String biography; // 자기소개
    private String role; // 회원 권한 (예: USER, ADMIN)
    private String socialProvider; // 소셜 로그인 제공자
    private String socialId; // 소셜 로그인 고유 ID
    private String phoneNumber; // 연락처
    private String emailVerified; // 이메일 인증 여부
    private Date dateOfBirth; // 생년월일
    private String gender; // 성별
    private String country; // 거주 국가
    private String preferredLanguage; // 선호 언어
    private String travelInterests; // 여행 관심사
    private String grade; // 회원 등급
    private String profileVisibility; // 프로필 공개 여부
    private String isDeleted; // 탈퇴 여부
    private LocalDateTime deletedAt; // 탈퇴 일시
    private LocalDateTime lastLogin; // 최종 로그인 일시
    private Long createdBy; // 생성자 ID
    private LocalDateTime createdAt; // 생성일
    private Long updatedBy; // 수정자 ID
    private LocalDateTime updatedAt; // 수정일


}
