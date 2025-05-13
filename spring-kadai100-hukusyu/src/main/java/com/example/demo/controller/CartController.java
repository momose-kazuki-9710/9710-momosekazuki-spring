package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
	
	@GetMapping("/cart")
	public String showCart() {
		return "cart";
	}
	
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam(name="name") String name,
			@RequestParam(name="price") String price,
			Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("price", price);
		return "cart";
	}

}
