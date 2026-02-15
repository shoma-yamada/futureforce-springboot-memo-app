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

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class AdminController{
	
	@Autowired
	private AdminRepository adminRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/signup")
	public String signUpForm(Model model){
		return "admin-signup";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute @Valid Admin admin,
	BindingResult result,
	Model model) {
		 if(result.hasErrors()) {
		        model.addAttribute("admin", admin);
		        return "admin/signup";
		    }
		 
		 admin.setPassword(passwordEncoder.encode(admin.getPassword()));

		 adminRepository.save(admin);
		    return "redirect:/memo";
	}

	
}
