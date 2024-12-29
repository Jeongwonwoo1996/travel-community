package com.example.travelcommunity.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    private Long categoryId; // 카테고리 고유 ID
    private String name; // 카테고리 이름
    private String description; // 카테고리 설명
    private Boolean isActive; // 카테고리 활성화 여부
    private Integer priority; // 정렬 우선순위
    private Long createdBy; // 생성자 ID
    private LocalDateTime createdAt; // 생성일
    private Long updatedBy; // 수정자 ID
    private LocalDateTime updatedAt; // 수정일
}
