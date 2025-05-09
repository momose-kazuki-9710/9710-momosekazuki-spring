package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/items")
	public String index(
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			Model model) {
		
		// DB のテーブルからデータの取得
		List<Item> itemList = new ArrayList<>();
		List<Category> categoryList =  new ArrayList<>();
		
		// categoryId が null の場合 →itemsテーブルから全件取得
		// null 以外 →itemsテーブルからcategory_idで絞り込んで取得
		if(categoryId != null) {
			
			itemList = itemRepository.findByCategoryId(categoryId);
		} 
		else {
			itemList = itemRepository.findAll();
		}
		
		
		categoryList = categoryRepository.findAll();
		
		
		model.addAttribute("itemList", itemList);
		model.addAttribute("categoryList", categoryList);
		
		//List<Item> itemList = itemRepository.findAll();
		//model.addAttribute("itemList", itemList);
		
		//全件取得、省略記述
		//model.addAttribute("itemList", itemRepository.findAll());
		
		return "items";
	}

}
