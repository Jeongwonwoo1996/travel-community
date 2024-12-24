package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.User;
import com.example.travelcommunity.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // 디버깅용 로그 추가
        System.out.println("User found: " + user.getEmail());
        System.out.println("Password: " + user.getPassword()); // 비밀번호 확인

        return new CustomUserDetails(user);
    }
}
