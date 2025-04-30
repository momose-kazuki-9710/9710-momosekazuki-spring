package com.example.demo.cotroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
	
	
	@GetMapping("/contact")
	public String index() {

		return "contactForm";
	}
	
	@PostMapping("/contact")
	public String contact(
			
		@RequestParam(name = "name") String name,
		@RequestParam(name = "email") String email,
		
		Model model
			) {
		List<String> warningtList = new ArrayList<>();
		
		String warningName = "";
		String warningMail = "";
		
		if (name == null || name.length() == 0) {
			
			warningName = "名前は必須です";
			warningtList.add(warningName);
		}
		else if(name.length() > 20) {
			warningName = "名前は20字以内で入力してください";
			warningtList.add(warningName);
		}
		
		
		if (email == null || email.length() == 0) {
			
			warningMail = "メールアドレスは必須です";
			warningtList.add(warningMail);
		}
//			model.addAttribute("warning", warningName);
//			return "contactForm";
//		}
//		if(warningtList == null || warningtList.size() ==0) {
		if(warningtList.size() == 0) {
			model.addAttribute("name", name);
			model.addAttribute("email", email);
			return "contactResult";
		}
		
//		else {
//			model.addAttribute("warningtList", warningtList);
//			return "contactForm";
//		}
		model.addAttribute("warningtList", warningtList);
		return "contactForm";
		
		
	}		
	
}
