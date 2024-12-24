package com.example.travelcommunity.controller;

import com.example.travelcommunity.dto.UserRequestDto;
import com.example.travelcommunity.mapper.converter.UserConverter;
import com.example.travelcommunity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserConverter userConverter;

    public AuthController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRequestDto userRequestDto) {
        userService.registerUser(userConverter.toEntity(userRequestDto));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
