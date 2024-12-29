package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.User;
import com.example.travelcommunity.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 처리
    public void registerUser(User user) {
        // 1. 이메일 중복 확인
        if (userMapper.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 2. 비밀번호 암호화
        if (StringUtils.hasText(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            throw new IllegalArgumentException("비밀번호는 필수 입력 항목입니다.");
        }

        // 3. 기본 ROLE 설정
        if (!StringUtils.hasText(user.getRole())) {
            user.setRole("USER");
        }

        // 4. 데이터베이스에 저장
        userMapper.insertUser(user);
    }

    // 로그인 처리
    public User login(String email, String password) {
        User user = userMapper.findByEmail(email);

        // 이메일이 없는 경우 예외 발생
        if (user == null) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 비밀번호가 맞지 않는 경우 예외 발생
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        return user;
    }

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public String getUsernameById(Long userId) {
        return userMapper.findUsernameById(userId);
    }



}
