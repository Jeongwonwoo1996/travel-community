package com.example.travelcommunity.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Long postId; // 게시글 고유 ID
    private Long userId; // 작성자 ID (users 테이블 참조)
    private Long categoryId; // 카테고리 ID (categories 테이블 참조)
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String thumbnailImage; // 썸네일 이미지 경로
    private Integer likesCount; // 좋아요 수
    private Integer viewsCount; // 조회수
    private Boolean isPinned; // 상단 고정 여부
    private Boolean isPrivate; // 비공개 게시글 여부
    private Long createdBy; // 생성자 ID
    private LocalDateTime createdAt; // 생성일
    private Long updatedBy; // 수정자 ID
    private LocalDateTime updatedAt; // 수정일

    // 포맷팅된 날짜
    private String formattedCreatedAt;
    private String author; // 작성자 이름
    private String categoryName; // 카테고리 이름 추가
}
