package com.example.travelcommunity.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long commentId; // 댓글 고유 ID
    private Long postId; // 게시글 ID (posts 테이블 참조)
    private Long userId; // 댓글 작성자 ID (users 테이블 참조)
    private Long parentCommentId; // 상위 댓글 ID (self-referencing)
    private String content; // 댓글 내용
    private Integer likesCount; // 좋아요 수
    private Boolean isDeleted; // 댓글 삭제 여부
    private Long createdBy; // 생성자 ID
    private LocalDateTime createdAt; // 생성일
    private Long updatedBy; // 수정자 ID
    private LocalDateTime updatedAt; // 수정일

    private String author; // 작성자 이름 추가
    private String formattedCreatedAt; // 포맷팅된 생성일
}
