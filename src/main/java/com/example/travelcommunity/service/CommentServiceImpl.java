package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.Comment;
import com.example.travelcommunity.mapper.CommentMapper;
import com.example.travelcommunity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        if (comment.getUserId() == null || comment.getPostId() == null) {
            throw new IllegalArgumentException("userId와 postId는 필수입니다.");
        }
        commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentMapper.selectCommentsByPostId(postId);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentMapper.selectCommentById(commentId);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    @Override
    public void likeComment(Long commentId) {
        commentMapper.incrementLikeCount(commentId);
    }

    @Override
    public void unlikeComment(Long commentId) {
        commentMapper.decrementLikeCount(commentId);
    }

    @Override
    public List<Comment> getCommentsByPostIdWithPaging(Long postId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("limit", pageSize);
        params.put("offset", offset);
        return commentMapper.selectCommentsWithPaging(params);
    }

    @Override
    public int countCommentsByPostId(Long postId) {
        return commentMapper.countCommentsByPostId(postId);
    }
}
