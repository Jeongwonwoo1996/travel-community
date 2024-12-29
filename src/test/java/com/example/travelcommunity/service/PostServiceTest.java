package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void createAndGetPost() {
        // Given
        Post post = new Post();
        post.setUserId(1L);
        post.setCategoryId(1L); // 유효한 카테고리 ID 설정
        post.setTitle("테스트 제목");
        post.setContent("테스트 내용");
        post.setThumbnailImage("default-thumbnail.png");
        post.setCreatedBy(1L);

        // When
        postService.createPost(post);

        // Then
        assertThat(post.getPostId()).isNotNull(); // postId가 설정되었는지 확인
        Post retrievedPost = postService.getPost(post.getPostId());
        assertThat(retrievedPost).isNotNull();
        assertThat(retrievedPost.getTitle()).isEqualTo("테스트 제목");
    }

    @Test
    void getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        assertThat(posts).isNotEmpty();
    }

    @Test
    void deletePost() {
        Post post = new Post();
        post.setUserId(1L);
        post.setTitle("삭제 테스트");
        post.setContent("삭제 테스트 내용");
        postService.createPost(post);

        postService.deletePost(post.getPostId());

        Post deletedPost = postService.getPost(post.getPostId());
        assertThat(deletedPost).isNull();
    }


}