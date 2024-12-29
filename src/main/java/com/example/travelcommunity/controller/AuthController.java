package com.example.travelcommunity.controller;

import com.example.travelcommunity.dto.UserRequestDto;
import com.example.travelcommunity.entity.User;
import com.example.travelcommunity.mapper.converter.UserConverter;
import com.example.travelcommunity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserConverter userConverter;

    public AuthController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        try {
            User user = userService.login(email, password); // UserService에서 로그인 처리
            model.addAttribute("user", user); // 성공 시 사용자 정보 추가
            return "index"; // 성공 시 대시보드로 이동
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // 실패 시 에러 메시지 추가
            return "login"; // 실패 시 로그인 페이지 유지
        }
    }



    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "register";
    }

    // 회원가입 처리 POST
// 회원가입 처리
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRequestDto userRequestDto, Model model) {
        try {
            userService.registerUser(userConverter.toEntity(userRequestDto)); // 서비스에 로직 위임
            log.info("User registered successfully: {}", userRequestDto.getEmail());
            return "redirect:/auth/login?registered=true";
        } catch (IllegalArgumentException e) {
            log.warn("Registration failed: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "register";
        } catch (Exception e) {
            log.error("Unexpected error during registration", e);
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
            return "register";
        }
    }
}
