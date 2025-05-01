package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class AccountController {

	@GetMapping("/account")
	public String index(Model model) {
		
		//空のItemオブジェクトを登録　←エラー対策
		model.addAttribute("account",new Account());
		
		return "accountForm";
	}
	
	@PostMapping("/account/confirm")
	public String confirm(
			@RequestParam(name="name") String name,
			@RequestParam(name="email") String email,
			@RequestParam(name="password") String password,
			
			Model model) {
		
		Account account = new Account();
		List<String> errorList = new ArrayList<>();
		
		
		if (name == null || name == "") {
			errorList.add("名前は必須です");
		}
		else if (name.length() > 20) {
			errorList.add("名前は20文字以内です");
		}
		else {
			account.setName(name);
		}
		
		if (email == null || email == "") {
			errorList.add("メールアドレスは必須です");
		}
		else {
			account.setEmail(email);
		}
		
		if (password == null || password == "") {
			errorList.add("パスワードは必須です");
		}
		else {
			account.setPass(password);
		}
		
		if (errorList.size() > 0 ) {
			model.addAttribute("errorList",errorList);
			model.addAttribute("account",account);
			model.addAttribute("name",name);
			return "accountForm";
		}
		
//		model.addAttribute("name",name);
//		model.addAttribute("email",email);
//		model.addAttribute("pass",pass);
		model.addAttribute("account",account);
		
		return "accountConfirm";
	}
	
	@PostMapping("/account")
	public String store(
		@RequestParam(name="name") String name,
		@RequestParam(name="email") String email,
		@RequestParam(name="pass") String password,
		Model model
			) {
		
		Account account = new Account(name,email,password);
		model.addAttribute("account",account);
		return "accountFinish";
	}


}
