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
	
// itemsテーブルからデータを取得したい
// → ItemRepository を Autowired で読み込む
	@Autowired
	ItemRepository itemrepository;
	
	@Autowired
	CategoryRepository categoryrepository;
	
	@GetMapping("/items")
	public String idex(
			@RequestParam(name = "categoryId", defaultValue="") Integer categoryId, 
			@RequestParam(name = "maxPrice", defaultValue="") Integer maxPrice, 
			Model model) {
		
		List<Item> itemList = new ArrayList<>();
		
		//categoryテーブルからデータを全件取得
		List<Category> categoryList = categoryrepository.findAll();
		model.addAttribute("categoryList", categoryList);
		
		if (categoryId != null) {
			
			itemList = itemrepository.findByCategoryId(categoryId);
			
		}
		else if (maxPrice != null) {
			itemList = itemrepository.findByMaxPrice(maxPrice);
		}
		else {
			// DBから itemsテーブル のデータを全件取得   →「 .findAll()」
			itemList = itemrepository.findAll();
		}
		
		
		model.addAttribute("itemList", itemList);
		return "items";
	}

}
