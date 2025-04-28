package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//　↓一番最初に書く！！
//　「@Controller」
//　このクラスのメソッドを、ユーザのリクエストと紐づけるための宣言
@Controller
public class HelloController {

//	戻り値の型：String
//	メソッド名：index　→処理に即した名前
//	引数なし 　→引数は処理によって変わる
//	return ""
	
	
//	↓ 「@GatMapping」はURLと対応させるための宣言
//	↓ ホスト名：localhost:808
//	↓ [http://ホスト名/]にアクセスした時の処理
	@GetMapping("/")
	public String index(Model model) {
		  //htmlファイルに動的な部分を追加するときに使用する引数
		
//		hello.html に渡す値を設定
//		model.addAttribute("htmlファイルで使用する名称", 値)
		model.addAttribute("name", "中橋");
		
//		hello.hitml を表示させる（htmlを抜いて記述する必要あり）
		return "hello";
	}
	
//	[localhost:8080/input]にアクセスした時の処理
//	表示したい画面：input.html
//	input.htmlに動的な個所なし
	
	
	@GetMapping("/input")
	public String input() {
		
//		input.hitml を表示させる（htmlを抜いて記述する必要あり）
		return "input";
	}
	
	
//	@PostMapping("")
//	→　Post通信（秘匿通信）を使って、ユーザからリクエストが投げられた時の処理
//	→　結果として、大体の入力フォームはこれで送る
	
	@PostMapping("/input")
	public String inputname(
		@RequestParam(name = "name") String name,  //name="name"が指定されたinput の入力内容が渡される
		Model model  //次のページで動的な部分があったら使うやつ
			) {
		
//		入力フォームに入力された「名前」を受けとる　→引数で受け取る
		
		
//		受け取った「名前」をhello.htmlで使えるように登録
		model.addAttribute("name", name);
		
//		hello.hitml を表示させる
		return "hello";
	}
}
