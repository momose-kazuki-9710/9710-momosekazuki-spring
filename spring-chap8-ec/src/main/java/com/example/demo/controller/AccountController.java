package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class AccountController {
	
	@Autowired
	Account account;
	
	@Autowired
	HttpSession session;
	
	//「localhost:8080/」にアクセスした時の処理
	//「localhost:8080/logout」にアクセスした時の処理
	@GetMapping({"/", "/logout"})
	public String index() {
		
		//セッション情報の全削除
		session.invalidate();
		return "login";
	}
	
	
	//ログインボタンに押下した時の処理
	//処理：1. 入力された名前をセッションのAccuntクラスのnameに登録する
	//      2. /itemsにリダイレクト
	@PostMapping("/login")
	public String login(
			@RequestParam(name="name")String name,
			Model model) {
		
		//1.
		account.setName(name);
		model.addAttribute("name", name);
		
		//2.
		return "redirect:/items";
	}

}
