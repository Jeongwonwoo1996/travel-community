package com.example.travelcommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index"; // 홈 화면 렌더링
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // 회원 가입 화면 렌더링
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // 로그인 화면 렌더링
    }

}
