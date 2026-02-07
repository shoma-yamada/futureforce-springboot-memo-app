package com.lesson.memo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "姓を入力してください")
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @NotBlank(message = "名を入力してください")
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスの形式が不正です")
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank(message = "パスワードを入力してください")
    @Column(nullable = false, length = 255)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
