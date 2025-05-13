package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;



@Controller
public class AccountController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Account account;
	
	@Autowired
	HttpSession session;
	
	@GetMapping({"/login","/logout"})
	public String index() {
		
		session.invalidate();
		return "login";
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam(name="email", defaultValue = "")String email,
			@RequestParam(name="password", defaultValue = "")String password,
			Model model) {
		
		Account account = new Account(email, password);
		List<User> userList = new ArrayList<>();
		userList = userRepository.findAll();
		
		
		
		//for文
//		for(User user;userList) {
//			
//		}
		
		return "redirect:/users";
	}
	
	public User checkLogin(String email, String password) {
		// 顧客データを取得
		Optional<User> usercheck = userRepository.findByEmailAndPassword(email, password);

		// 顧客データが取得できなかった場合
		if(usercheck.isEmpty()) {
			// nullを返却
			return null;
		}
		// 顧客データが取得できた場合
		else {
			// 顧客データを返却
			return usercheck.get();
		}
	
	}
	
}
