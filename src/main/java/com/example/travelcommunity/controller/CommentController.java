package com.example.travelcommunity.controller;

import com.example.travelcommunity.entity.Comment;
import com.example.travelcommunity.entity.Post;
import com.example.travelcommunity.entity.User;
import com.example.travelcommunity.service.CommentService;
import com.example.travelcommunity.service.PostService;
import com.example.travelcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addComment(@ModelAttribute Comment comment) {
        // 현재 로그인된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // 로그인한 사용자의 이메일

        // 이메일로 사용자 ID 조회 (UserService 사용)
        User loggedInUser = userService.findByEmail(email);
        if (loggedInUser == null) {
            throw new RuntimeException("로그인된 사용자를 찾을 수 없습니다.");
        }

        comment.setUserId(loggedInUser.getUserId()); // userId 설정
        comment.setCreatedBy(loggedInUser.getCreatedBy());
        commentService.addComment(comment);
        return "redirect:/board/" + comment.getPostId();
    }

    @PostMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @RequestParam Long postId) {
        commentService.deleteComment(commentId);
        return "redirect:/board/" + postId;

    }

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
        Post post = postService.getPost(postId);
        post.setFormattedCreatedAt(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        List<Comment> comments = commentService.getCommentsByPostId(postId);
        comments.forEach(comment -> {
            if (comment.getCreatedAt() != null) {
                comment.setFormattedCreatedAt(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }
        });

        model.addAttribute("post", post);
        model.addAttribute("comments", comments); // 댓글 데이터 추가
        return "view";
    }

}
