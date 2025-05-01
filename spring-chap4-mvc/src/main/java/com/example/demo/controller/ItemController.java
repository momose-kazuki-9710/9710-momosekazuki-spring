package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	
	//DB接続用のrepositoryファイルを呼び出す
	@Autowired
	ItemRepository itemRepository;
	
	//「localhost:8080/item」にアクセスした時の処理
	@GetMapping("/item")
	public String index(Model model) {	
		
		//空のItemオブジェクトを登録　←エラー対策
		model.addAttribute(new Item());
		
		//item.html を表示
		return "item";
	}
	
	
	//postリクエスト「/item」が送られてきた時の処理
	//・パラメータ(name,price)を受け取る
	
	@PostMapping("/item")
	public String add(
			@RequestParam(name="name") String name,
			@RequestParam(name="price") Integer price,
			Model model
			) {
	
		//Itemクラスのオブジェクト作成
		
		//1つずつ
		//Item item1 = new Item();
		//item1.setName(name);
		//item1.setPrice(price);
		Item item1 = new Item(name,price);
		
		model.addAttribute("item1",item1);

		
		return "item";
	}
	
	
	//「localhost:8080」にアクセスした時の処理
	//処理の内容： 1. itemsテーブルのデータを全件取得( SELECT * FROM items; )
	//             2. 取得したデータをitemList.htmlで使えるように登録する
	//             3. itemlist.htmlに表示する
	@GetMapping("/itemlist")
	public String itemList(Model model) {
		
		// 1.
		List<Item> itemList = itemRepository.findAll();
		//findAll ←全件取得
		
		//2.
		model.addAttribute("itemList",itemList);
		
		return "itemList";
	}

}
