package com.example.travelcommunity.mapper;

import com.example.travelcommunity.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user); // 회원 등록
    User findById(Long userId); // ID로 회원 조회

    User findByEmail(String email);
    User findByUsername(String username); // 사용자 이름으로 조회
    void updateUser(User user); // 회원 정보 수정
    void deleteUser(Long userId); // 회원 삭제
}
