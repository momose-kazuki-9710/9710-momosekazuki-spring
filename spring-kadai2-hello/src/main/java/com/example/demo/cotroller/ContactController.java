package com.example.demo.cotroller;

import java.time.LocalDate;
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
		
		//ラジオボタンの選択した値の受け取り
		//name = genre の value を値として受け取る
		@RequestParam(name = "genre") Integer genre,
		
		//チェックボックスの選択した値の受け取り
		// →name="language"の選択されたvalueの値を 「配列」 として受け取る
		@RequestParam(name = "language") List<String>languageList,
		
		@RequestParam(name = "detail") String detail,
		
		//日付の受け取り
		@RequestParam(name = "date") LocalDate date,
		
		
		Model model
			) {
		List<String> warningtList = new ArrayList<>();
		
		String warningName = "";
		String warningMail = "";
		
		if (name == null || name.length() == 0) {
			
			warningName = "名前は必須です";
			warningtList.add(warningName);
			//一発で ↓ これでもいい
			//warningtList.add("名前は必須です");
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
			model.addAttribute("genre",genre);
			model.addAttribute("languageList",languageList);
			model.addAttribute("detail",detail);
			model.addAttribute("date",date);
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
