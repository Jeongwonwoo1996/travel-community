package com.example.travelcommunity.service;

import com.example.travelcommunity.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user; // 프로젝트의 AppUser 엔티티 사용

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 권한이 없는 경우 빈 리스트 반환
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // AppUser의 getPassword 호출
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // AppUser의 이메일 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명 만료 여부
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 여부
    }

    public User getUser() {
        return user; // AppUser 객체 반환
    }
}
