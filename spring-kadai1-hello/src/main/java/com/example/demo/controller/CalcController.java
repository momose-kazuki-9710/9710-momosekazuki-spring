package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalcController {
	
	@GetMapping("/calc")
	public String calc(){
		return "calc";
	}
//	@PostMapping("/add")
//	public Integer inputnumber(
//		@RequestParam(name = "num1") Integer num1,
//		@RequestParam(name = "num2") Integer num2,
//		Model model  //次のページで動的な部分があったら使うやつ
//			) {
//		Intrger result = num1 + num2;
//		retrun result;
//	}
//	public int add(Integer num1, Integer num2) {
//		retrun num1 + num2;
//	}

}
