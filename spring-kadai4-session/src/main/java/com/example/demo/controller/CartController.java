package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.Item;

@Controller
public class CartController {
	
	@Autowired
	Cart cart;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	Account account;

	
	@GetMapping("/cart")
	public String showCart() {
		
		
		return "cart";
	}
	
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam(name="name") String name,
			@RequestParam(name="price") Integer price,
			Model model) {
		
		Item item = new Item();
		
		item.setName(name);
		item.setPrice(price);
		
		model.addAttribute("item", item);
		
		//セクションスコープに保持されたリストを取得
		List<Item> allItems= cart.getItems();
		//受け取ったパラメータをリストに追加
		allItems.add(new Item(name,price));
//		Item item1 = new Item(name,price);
//		allItems.add(item1);
			
		return "cart";
	}
	
	@GetMapping("/cart/clear")
	public String clearCart() {
		
		//List型の機能「clear」を使用する
		cart.getItems().clear();
		
		return "cart";
	}

	
	@GetMapping({"/cart/login","/cart/logout"})
	public String index() {
		
		//ユーザのセッション情報を削除(セッション情報全削除)
		session.invalidate();
		
		return "cartLogin";
	}
	
	@PostMapping("/cart/login")
	public String login(
			@RequestParam(name="name") String name,
			Model model) {
		
		account.setName(name);
		
		return "cart";
	}
}
