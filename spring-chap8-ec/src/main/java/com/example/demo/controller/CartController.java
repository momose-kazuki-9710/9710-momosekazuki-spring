package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.model.Cart;
import com.example.demo.repository.ItemRepository;

@Controller
public class CartController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	Cart cart;
	
	//「localhost:8080/cart」にアクセスした時の処理
	@GetMapping("/cart")
	public String index() {
		return "cart";
	}
	
	//「カートに追加ボタン」を押下された時の処理
	//パラメータ：隠しパラメータを受け取る
	//処理内容： 1. DBのitemsテーブルからidが一致するデータを取得
	//           2. データが取得できた場合、カートに追加
	
	//. cartにリダイレクト
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam(name="itemId")Integer itemId,
			Model model) {
		
		//1. 
		Optional<Item> dbData = itemRepository.findById(itemId);
		
		//2. 
		if( !dbData.isEmpty()) {
			cart.add(dbData.get());
		}
		return "redirect:/cart";
	}
	

}
