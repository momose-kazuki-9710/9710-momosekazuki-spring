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
public class BlogController {
	
	
	@Autowired
	Account account;
	
	// ↓ @Autowired の書き方
	//@Autowired
	//使いたいファイル 処理で使う変数名;
	
	@Autowired
	HttpSession session;
	
	//複数のURLに対応させたいとき
	//　→（）の中を{ "", "" }で書き分ける
	
	@GetMapping({"/", "/logout"})
	public String index(){
		
		//ユーザのセッション情報を削除(セッション情報全削除)
		session.invalidate();
				
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(
		@RequestParam(name="name")String name,
		Model model
			){
		
		// ↓ クラス経由
		//Account account = new Account();
		//account.setName(name);
		//model.addAttribute("account",account);
		
		// ↓ 直接
		//model.addAttribute("name", name);
		
		//セッションに名前を登録する
		account.setName(name);
//		model.addAttribute("account",account);
		return "blog";
	}
	
	//「localhost:8080/blog」にアクセスした時のみ処理
	//検証用
	@GetMapping("/blog")
	public String showBlog() {
		return "blog";
	}
	
	//「localhost:8080/logout」にアクセスした時のみ処理
	//ログアウト用
//	@GetMapping("/logout")
//	public String logout() {
//		
//		//ユーザのセッション情報を削除
//		session.invalidate();
//		
//		return "login";
//	}
//	
	
	

}
