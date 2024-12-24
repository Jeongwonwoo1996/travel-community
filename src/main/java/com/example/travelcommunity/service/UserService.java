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
        userMapper.insertUser(user);
    }

    public User findUserById(Long userId) {
        return userMapper.findById(userId);
    }

    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Long userId) {
        userMapper.deleteUser(userId);
    }
}
