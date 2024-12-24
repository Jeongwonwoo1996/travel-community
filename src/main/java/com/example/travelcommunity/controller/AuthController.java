package com.example.travelcommunity.controller;

import com.example.travelcommunity.dto.UserRequestDto;
import com.example.travelcommunity.entity.User;
import com.example.travelcommunity.mapper.converter.UserConverter;
import com.example.travelcommunity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, UserConverter userConverter, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

//    @PostMapping("/login/submit")
//    public String processLogin(@RequestParam("email") String email,
//                               @RequestParam("password") String password,
//                               HttpServletRequest request,
//                               Model model) {
//        log.info("Attempting login with email: {}", email);
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(email, password)
//            );
//
//            log.info("Login successful for email: {}", email);
//
//            HttpSession session = request.getSession();
//            session.setAttribute("username", authentication.getName());
//
//            return "redirect:/?success=true";
//        } catch (BadCredentialsException e) {
//            log.warn("Login failed for email: {}. Invalid credentials.", email);
//            model.addAttribute("error", "Invalid email or password");
//            return "login";
//        } catch (Exception e) {
//            log.error("Login failed for email: {}. Error: {}", email, e.getMessage());
//            model.addAttribute("error", "Authentication failed");
//            return "login";
//        }
//    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "register";
    }

    // 회원가입 처리 POST
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRequestDto userRequestDto) {
        // 1) DTO → Entity 변환
        User user = userConverter.toEntity(userRequestDto);

        // 2) 비밀번호 암호화
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        // 3) 회원가입 Service 호출
        userService.registerUser(user);

        // 회원가입 완료 후 로그인 페이지로 리다이렉트
        return "redirect:/login?registered=true";


    }
}
