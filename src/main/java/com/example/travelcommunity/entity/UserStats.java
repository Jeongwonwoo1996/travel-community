package com.example.travelcommunity.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserStats {
    private Long userId; // 회원 고유 ID (users 테이블 참조)
    private Integer totalPosts; // 총 게시글 수
    private Integer totalComments; // 총 댓글 수
    private Integer followersCount; // 팔로워 수
    private Integer followingCount; // 팔로잉 수
    private Integer travelScore; // 여행 활동 점수
    private LocalDateTime lastUpdated; // 마지막 통계 업데이트 시간
    private Long createdBy; // 생성자 ID
    private LocalDateTime createdAt; // 생성일
    private Long updatedBy; // 수정자 ID
    private LocalDateTime updatedAt; // 수정일
}
