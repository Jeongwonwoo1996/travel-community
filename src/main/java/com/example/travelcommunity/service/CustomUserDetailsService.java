package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.User;
import com.example.travelcommunity.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email = " + email);
        User user = userMapper.findByEmail(email);

        if (user == null) {
            log.error("Authentication failed: No user found with email {}", email);
            throw new UsernameNotFoundException("No user found with email: " + email);
        }

        if (user.getPassword() == null || user.getRole() == null) {
            log.error("Authentication failed: Incomplete user data for email {}", email);
            throw new UsernameNotFoundException("Invalid user data for email: " + email);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole()) // Ensure ROLE_ prefix
                .build();
    }
}
