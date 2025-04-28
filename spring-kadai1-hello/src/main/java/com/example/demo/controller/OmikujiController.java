package com.example.demo.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//1.
@Controller
public class OmikujiController {
	
//.2
//		input(入力画面)を開けるようにする
		@GetMapping("/omikuji")
		public String omikuji() {
			
//			input.hitml を表示させる（htmlを抜いて記述する必要あり）
			return "omikuji";
		}
		
		@PostMapping("/omikuji")
		public String inputname(
			Model model  //次のページで動的な部分があったら使うやつ
				) {
			
			String result = "";
			Random rand = new Random();
			int num = rand.nextInt(5);

			switch(num) {
			case 0:
				result = "大吉";
				break;
			case 1:
				result = "小吉";
				break;
			case 2:
				result = "凶";
				break;
			default:
				result = "吉";
				break;
			}
			
			model.addAttribute("result", result);

			return "omikuji";

		}

}
