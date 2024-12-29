package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.Post;
import com.example.travelcommunity.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public void createPost(Post post) {
        if (post.getCategoryId() == null) {
            post.setCategoryId(1L); // 기본 카테고리 ID
        }
        if (post.getThumbnailImage() == null) {
            post.setThumbnailImage("default-thumbnail.png"); // 기본 썸네일 이미지
        }
        if(post.getCreatedBy() == null){
            post.setCreatedBy(1L);
        }
        postMapper.insertPost(post);
    }

    @Override
    public Post getPost(Long postId) {
        return postMapper.selectPostById(postId);
    }

    @Override
    public void deletePost(Long postId) {
        postMapper.deletePostById(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postMapper.selectAllPosts();
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post); // PostMapper에 적절한 SQL 쿼리를 작성해야 합니다.
    }

    @Override
    public List<Post> getPostsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("pageSize", pageSize);
        params.put("offset", offset);
        return postMapper.selectPostsWithPaging(params);
    }

    @Override
    public int getTotalPostCount() {
        return postMapper.countAllPosts();
    }

    @Override
    public void incrementViewCount(Long postId) {
        postMapper.incrementViewCount(postId);
    }
}
