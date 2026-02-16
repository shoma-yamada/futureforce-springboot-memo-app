package com.lesson.memo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class AdminController{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signUpForm(Model model){
        model.addAttribute("admin", new Admin());
        return "admin-signup"; // HTMLファイル名
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute @Valid Admin admin,
                         BindingResult result,
                         Model model) {
        if(result.hasErrors()) {
            model.addAttribute("admin", admin);
            return "admin-signup";
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);

        return "admin-signin";
    }
    
    @GetMapping("/signin")
    public String loginForm(
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        if (error != null) {
            model.addAttribute("errorMessage", "ログイン時にエラーが起きました。");
        }

        return "admin-signin";
    }
}