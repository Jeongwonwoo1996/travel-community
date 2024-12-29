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
@RequestMapping("/board")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    // 게시글 생성
    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post) {

        // 현재 로그인된 사용자 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // 이메일 또는 사용자 이름

        // 이메일을 사용해 User 엔티티 조회
        User loggedInUser = userService.findByEmail(email);

        System.out.println("loggedInUser = " + loggedInUser);
        if (loggedInUser == null) {
            throw new RuntimeException("로그인된 사용자를 찾을 수 없습니다.");
        }

        // Post에 userId 설정
        post.setUserId(loggedInUser.getUserId());

        // 디버깅 로그 추가
        System.out.println("User ID: " + loggedInUser.getUserId());
        System.out.println("Post: " + post);


        // 기본 값 설정
        post.setThumbnailImage("default-thumbnail.png");
        post.setCategoryId(1L); // 예시로 기본 카테고리 설정

        // 게시글 생성
        postService.createPost(post);

        return "redirect:/board"; // 게시글 목록으로 리다이렉트
    }

    // 게시글 조회
    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {

        // 조회수 증가
        postService.incrementViewCount(postId);

        // 게시글 데이터 가져오기
        Post post = postService.getPost(postId);
        if (post.getCreatedAt() != null) {
            post.setFormattedCreatedAt(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        List<Comment> comments = commentService.getCommentsByPostId(postId);
        comments.forEach(comment -> {
            String authorName = userService.getUsernameById(comment.getUserId());
            comment.setAuthor(authorName); // 작성자 이름 설정
            if (comment.getCreatedAt() != null) {
                comment.setFormattedCreatedAt(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }
        });

        model.addAttribute("post", post);
        model.addAttribute("comments", comments); // 댓글 데이터 추가
        return "view";
    }


    // 게시글 삭제
    @PostMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User loggedInUser = userService.findByEmail(email);

        Post post = postService.getPost(postId);
        if (post == null || !post.getUserId().equals(loggedInUser.getUserId())) {
            throw new RuntimeException("권한이 없습니다.");
        }


        postService.deletePost(postId);
        return "redirect:/board";
    }

    @GetMapping("/edit/{postId}")
    public String editPost(@PathVariable Long postId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User loggedInUser = userService.findByEmail(email);

        Post post = postService.getPost(postId);

        // 권한 검증
        if (post == null || !post.getUserId().equals(loggedInUser.getUserId())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        model.addAttribute("post", post);
        model.addAttribute("isEdit", true); // 뷰에서 상태를 구분하기 위한 플래그
        return "write"; // 글 작성 및 수정에 공통적으로 사용되는 템플릿
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@PathVariable Long postId, @ModelAttribute Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User loggedInUser = userService.findByEmail(email);

        Post existingPost = postService.getPost(postId);

        // 권한 검증
        if (existingPost == null || !existingPost.getUserId().equals(loggedInUser.getUserId())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        // 기존 데이터에 업데이트할 데이터 적용
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());

        postService.updatePost(existingPost);

        return "redirect:/board/" + postId; // 수정된 게시글 상세 페이지로 리다이렉트
    }


    // 게시글 목록 조회
//    @GetMapping
//    public String getAllPosts(Model model) {
//        List<Post> posts = postService.getAllPosts();
//        posts.forEach(post -> {
//            if (post.getCreatedAt() != null) {
//                // 날짜를 포맷팅하여 저장
//                post.setFormattedCreatedAt(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            }
//        });
//        model.addAttribute("posts", posts);
//        return "board";
//    }

    @GetMapping
    public String getBoardPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            Model model) {
        List<Post> posts = postService.getPostsByPage(page, pageSize);
        posts.forEach(post -> {
            if (post.getCreatedAt() != null) {
                // 날짜를 포맷팅하여 저장
                post.setFormattedCreatedAt(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
        int totalPosts = postService.getTotalPostCount();
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize); // 필요 시 추가

        return "board";
    }







}
