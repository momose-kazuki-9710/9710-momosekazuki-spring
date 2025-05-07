package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	// itemsテーブルからデータを取得したい
	// → ItemRepository を Autowired で読み込む
	@Autowired
	ItemRepository itemrepository;
	
	
	@GetMapping("/items")
	public String showItems(
			
			//検索のパラメータ　
			// →defaultValue を必ず作る
			@RequestParam(name="keyword", defaultValue="") String keyword,
			Model model) {
		
		List<Item> itemList = new ArrayList<>();
		
		//キーワードが入力されていたら ⇔ そうでない場合
		if (keyword != null && keyword.length() > 0) {
			//キーワードで絞り込んで取得
			itemList =itemrepository.findByKeyword("%"+ keyword +"%");
			
			//SQL上
				//SELECT * 
				//FROME items
				//WHERE name LIKE '% ～ %'
		}
		else {
			// DBから itemsテーブル のデータを全件取得
			itemList = itemrepository.findAll();
		}
		
		// 取得したデータをhtmlで扱えるように登録
		model.addAttribute("itemList", itemList);

		
		return "items";
	}

}
