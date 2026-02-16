package com.lesson.memo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                		"/css/**",
                		"/js/**",
                		"/images/**",
                		"/admin/signup",
                		"/admin/signin"
                		).permitAll()
                .anyRequest().authenticated()
            )

            // ログイン設定
            .formLogin(form -> form
                    .loginPage("/admin/signin")
                    .loginProcessingUrl("/admin/signin")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/memo", true)
                    .permitAll()
                )

            // ログアウト設定
            .logout(logout -> logout
                .logoutSuccessUrl("/admin/signin")
                .permitAll()
            );

        return http.build();
    }

    // パスワードエンコーダー
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}