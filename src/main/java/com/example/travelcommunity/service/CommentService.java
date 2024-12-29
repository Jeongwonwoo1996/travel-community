package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    List<Comment> getCommentsByPostId(Long postId);

    Comment getCommentById(Long commentId);

    void deleteComment(Long commentId);

    void updateComment(Comment comment);

    void likeComment(Long commentId);

    void unlikeComment(Long commentId);

    List<Comment> getCommentsByPostIdWithPaging(Long postId, int page, int pageSize);

    int countCommentsByPostId(Long postId);

}
