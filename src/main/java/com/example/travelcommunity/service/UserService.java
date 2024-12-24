package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.User;
import com.example.travelcommunity.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void registerUser(User user) {

        // (선택) DB에 저장할 기본 ROLE 설정
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        userMapper.insertUser(user);
    }

}
