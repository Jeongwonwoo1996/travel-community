package com.example.travelcommunity.mapper;

import com.example.travelcommunity.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    // 댓글 생성
    void insertComment(Comment comment);

    // 특정 게시글의 댓글 목록 조회
    List<Comment> selectCommentsByPostId(Long postId);

    // 댓글 상세 조회 (대댓글 등의 구현을 위해 사용 가능)
    Comment selectCommentById(Long commentId);

    // 댓글 삭제 (isDeleted로 처리)
    void deleteComment(Long commentId);

    // 댓글 업데이트
    void updateComment(Comment comment);

    // 특정 댓글의 좋아요 증가
    void incrementLikeCount(Long commentId);

    // 특정 댓글의 좋아요 감소
    void decrementLikeCount(Long commentId);

    // 댓글 목록 페이징 조회
    List<Comment> selectCommentsWithPaging(Map<String, Object> params);

    // 댓글 개수 조회 (페이징 총 페이지 수 계산용)
    int countCommentsByPostId(Long postId);
}
