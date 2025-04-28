package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//1.
@Controller
public class HelloController {
	
	//	↓ 「@GatMapping」はURLと対応させるための宣言
	//	↓ ホスト名：localhost:808
	//	↓ [http://ホスト名/]にアクセスした時の処理
	
//2.
	@GetMapping("/")
	public String index(Model model) {
		//Model model：htmlファイルに動的な部分を追加するときに使用する引数
		
		//hello.html に渡す値を設定
		// → model.addAttribute("htmlファイルで使用する名称", 値)
		
		//hello.hitml を表示させる（htmlを抜いて記述する必要あり）
		return "index";
	
	}
	
//3.
//	input(入力画面)を開けるようにする
	@GetMapping("/hello")
	public String input() {
		
//		input.html を表示させる（htmlを抜いて記述する必要あり）
		return "input";
	}
	
	
	@PostMapping("/hello")
	public String show(
		@RequestParam(name = "name") String name,
		@RequestParam(name = "age") Integer age,
		@RequestParam(name = "hobby") String hobby,
		//name="name"が指定されたinput の入力内容が渡される
		Model model  //次のページで動的な部分があったら使うやつ
			) {
		
//		入力フォームに入力された「名前」を受けとる　→引数で受け取る		
//		受け取った「名前」をhello.htmlで使えるように登録
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("hobby", hobby);
		
		String memo = "";
		if (age >= 20) {
			int adult = age - 18; 
			memo = "成人してから" + adult + "年たちました";
		}
		else {
			memo = "未成年です";
		}
		
		model.addAttribute("memo", memo);
//		hello.hitml を表示させる
		return "hello";

	}
	
	//3.
////	input(入力画面)を開けるようにする
//	@GetMapping("/hello")
//	public String hello() {
//		
////		input.hitml を表示させる（htmlを抜いて記述する必要あり）
//		return "hello";
//	}


}
