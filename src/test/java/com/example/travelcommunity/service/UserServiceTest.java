package com.example.travelcommunity.service;

import com.example.travelcommunity.dto.UserRequestDto;
import com.example.travelcommunity.entity.User;
import com.example.travelcommunity.mapper.UserMapper;
import com.example.travelcommunity.mapper.converter.UserConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @MockitoBean
    private UserMapper userMapper; // Mock 처리

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRequestDto mockUserRequestDto;

    private User mockUser;

//    @BeforeEach
//    void setUp() {
//        mockUserRequestDto = createMockUserRequestDto();
//    }
//
//    private UserRequestDto createMockUserRequestDto() {
//        UserRequestDto userRequestDto = new UserRequestDto();
//        userRequestDto.setUsername("testuser");
//        userRequestDto.setEmail("testuser@example.com");
//        userRequestDto.setPassword("password123");
//        userRequestDto.setPhoneNumber("01012345678");
//        userRequestDto.setDateOfBirth(new Date());
//        userRequestDto.setGender("M");
//        userRequestDto.setCountry("Korea");
//        userRequestDto.setPreferredLanguage("ko");
//        userRequestDto.setTravelInterests("traveling, hiking");
//        return userRequestDto;
//    }
//
//    @Test
//    void registerUser_shouldSaveUserSuccessfully() {
//        // 1. Mock 설정: 중복 이메일 없음
//        Mockito.when(userMapper.findByEmail(mockUserRequestDto.getEmail())).thenReturn(null);
//
//        // 2. DTO → Entity 변환
//        User user = userConverter.toEntity(mockUserRequestDto);
//
//        // 3. 회원가입 호출
//        userService.registerUser(user);
//
//        // 4. 검증: 비밀번호가 암호화되었는지 확인
//        assertThat(passwordEncoder.matches("password123", user.getPassword())).isTrue();
//
//        // 5. Mock 검증: insertUser가 호출되었는지 확인
//        Mockito.verify(userMapper).insertUser(Mockito.any(User.class));
//    }
//
//    @Test
//    void registerUser_shouldThrowExceptionForDuplicateEmail() {
//        // 1. Mock 설정: 중복 이메일 존재
//        Mockito.when(userMapper.findByEmail(mockUserRequestDto.getEmail())).thenReturn(new User());
//
//        // 2. 예외 검증
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            User user = userConverter.toEntity(mockUserRequestDto);
//            userService.registerUser(user);
//        });
//
//        assertThat(exception.getMessage()).isEqualTo("이미 사용 중인 이메일입니다.");
//    }
//
//    @Test
//    void registerUser_shouldThrowExceptionForEmptyPassword() {
//        // 1. 비밀번호 제거
//        mockUserRequestDto.setPassword("");
//
//        // 2. DTO → Entity 변환
//        User user = userConverter.toEntity(mockUserRequestDto);
//
//        // 3. 예외 검증
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(user);
//        });
//
//        assertThat(exception.getMessage()).isEqualTo("비밀번호는 필수 입력 항목입니다.");
//    }


    @Test
    void login_withValidCredentials_shouldAuthenticateUser() {
        // Mock 설정
        User mockUser = new User();
        mockUser.setEmail("testuser@example.com");
        mockUser.setPassword(passwordEncoder.encode("password123"));

        Mockito.when(userMapper.findByEmail("testuser@example.com")).thenReturn(mockUser);

        // 로그인 호출
        User user = userService.login("testuser@example.com", "password123");

        // SecurityContext 설정
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        // 검증
        assertThat(user).isNotNull();
        assertThat(SecurityContextHolder.getContext().getAuthentication().getName())
                .isEqualTo("testuser@example.com");
    }

    @Test
    void login_withInvalidCredentials_shouldThrowException() {
        // Mock 설정
        User mockUser = new User();
        mockUser.setEmail("testuser@example.com");
        mockUser.setPassword(passwordEncoder.encode("password123"));

        Mockito.when(userMapper.findByEmail("testuser@example.com")).thenReturn(mockUser);

        // 잘못된 비밀번호로 로그인 시도
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.login("testuser@example.com", "wrongpassword");
        });

        assertThat(exception.getMessage()).isEqualTo("이메일 또는 비밀번호가 올바르지 않습니다.");
    }







}