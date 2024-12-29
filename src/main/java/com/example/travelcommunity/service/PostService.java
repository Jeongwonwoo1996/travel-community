package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.Post;

import java.util.List;


public interface PostService {

    void createPost(Post post); // 게시글 생성
    Post getPost(Long postId);  // 게시글 조회
    void deletePost(Long postId); // 게시글 삭제
    List<Post> getAllPosts(); // 게시글 목록 조회

    void updatePost(Post post);

    List<Post> getPostsByPage(int page, int pageSize);

    int getTotalPostCount();

    void incrementViewCount(Long postId);

}
