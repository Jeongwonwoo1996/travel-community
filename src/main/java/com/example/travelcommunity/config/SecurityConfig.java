package com.example.travelcommunity.config;

import com.example.travelcommunity.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev") // 개발 환경에서만 활성화
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // 모든 요청 허용
//                )
//                .formLogin(AbstractHttpConfigurer::disable) // 폼 로그인 비활성화
//                .logout(AbstractHttpConfigurer::disable); // 로그아웃 비활성화
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화
                .csrf(AbstractHttpConfigurer::disable)

                // 요청 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login","/login/submit", "/register").permitAll()
                        .anyRequest().authenticated()
                )

                // 로그인 설정
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login/submit") // POST 요청 처리
                        .defaultSuccessUrl("/", true) // 성공 시 리다이렉트
                        .failureUrl("/login?error=true")
                        .usernameParameter("email") // email로 사용자 인증
                        .passwordParameter("password")
                        .permitAll()
                )

                // 로그아웃 설정 추가
                .logout(logout -> logout
                .logoutUrl("/logout")               // 로그아웃 요청 URL
                .logoutSuccessUrl("/login?logout")  // 로그아웃 성공 후 이동할 URL
                .invalidateHttpSession(true)        // 세션 무효화
                .deleteCookies("JSESSIONID")        // 쿠키 삭제
                .permitAll()
        );
        return http.build();
    }
}
