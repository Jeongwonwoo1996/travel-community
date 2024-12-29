package com.example.travelcommunity.mapper;

import com.example.travelcommunity.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    void insertPost(Post post); // 게시글 생성
    Post selectPostById(Long postId); // 게시글 조회
    void deletePostById(Long postId); // 게시글 삭제
    List<Post> selectAllPosts(); // 게시글 목록 조회

    void updatePost(Post post);

    List<Post> selectPostsWithPaging(Map map);
    int countAllPosts();

    void incrementViewCount(Long postId);
}
