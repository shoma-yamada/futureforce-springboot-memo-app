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
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/admin/signup", "/admin/login").permitAll()
                .anyRequest().authenticated()
            )

            // ログイン設定
            .formLogin(form -> form
                    .loginPage("/admin/login")
                    .loginProcessingUrl("/admin/login")
                    .usernameParameter("email")   // ← ここ重要
                    .passwordParameter("password")
                    .defaultSuccessUrl("/memo", true)
                    .permitAll()
                )

            // ログアウト設定（任意だが実務では基本入れる）
            .logout(logout -> logout
                .logoutSuccessUrl("/admin/login")
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