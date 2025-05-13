package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository;
	
	@GetMapping("/items")
	public String index(
			Model model) {
		
		List<Item> itemList = new ArrayList<>();
		itemList = itemRepository.findAll();
		
		model.addAttribute("itemList", itemList);
		return "items";
	}
}
