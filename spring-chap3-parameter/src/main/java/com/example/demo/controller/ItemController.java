package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {
	
	//登録画面表示
	//localhost:8080
	@GetMapping("/")
	public String index() {
		return "itemForm";
	}
	
	
	//登録情報の確認画面の表示
	@PostMapping("/item")
	public String confirm(
			
		//入力フォームに入力されたパラメータを受け取る
		@RequestParam(name = "name") String name,
		@RequestParam(name = "price") Integer price,
		
		//ラジオボタンの選択した値の受け取り
		//name = janru の value を値として受け取る
		@RequestParam(name = "janru") Integer janru,
		
		//チェックボックスの選択した値の受け取り
		// →name="color"の選択されたvalueの値を 「配列」 として受け取る
		@RequestParam(name = "color") List<String>colorList,
		Model model
			) {
		
		//受け取ったパラメータをitemConfirm.htmlに引き渡す
		model.addAttribute("name", name);
		model.addAttribute("price", price);
		model.addAttribute("janru", janru);
		model.addAttribute("colorList", colorList);
		
		return "itemConfirm";
	}
	
	//「localhost:8080/item/???」にアクセスした時の処理
	//???(商品ID)のパラメータで指定された商品の情報をitemConfirm.htmlで表示する
	// 「/item/{パラメータ名}」で書く
	
	@GetMapping("/item/{id}")
	public String show(
		
		//パスパラメータを受け取る
			// name="パラメータ名"で受け取るパラメータを指定
			//GetMappingの{} とname="" の "" を必ず一致させる
		@PathVariable(name="id") int id,
		
		Model model) {
		
		//処理
		// id が 101 の時は、ボールペン・100円を表示させる
		// id が 102 の時は、消しゴム・50円を表示させる
		// id が 103 の時は、未設定・0円を表示させる
		
		switch(id) {
		case 101:
			model.addAttribute("name", "ボールペン");
			model.addAttribute("price", 100);
		
		case 102:
			model.addAttribute("name", "消しゴム");
			model.addAttribute("price", 50);
		
		default :
			model.addAttribute("name", "未設定");
			model.addAttribute("price", 0);
		}
	
		
		//itemConfirm.htmlを表示する
		return "itemConfirm";
	}
	

}
