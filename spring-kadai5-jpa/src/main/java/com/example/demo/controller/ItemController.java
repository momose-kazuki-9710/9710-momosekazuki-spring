package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
			@RequestParam(name = "sort", defaultValue="") String sort,
			@RequestParam(name="keyword", defaultValue="") String keyword,
			Model model) {
		
		List<Item> itemList = new ArrayList<>();
		
		//categoryテーブルからデータを全件取得
		List<Category> categoryList = categoryrepository.findAll();
		model.addAttribute("categoryList", categoryList);
		
		
		if (categoryId != null) {
			
			itemList = itemrepository.findByCategoryId(categoryId);
			
		}
		else if (maxPrice != null) {
			if(sort == null || sort.length() == 0) {
				itemList = itemrepository.findByMaxPrice(maxPrice);
			}
			else {
				if(sort.equals("priceAsc")) {
					
					//Sortの機能を使う場合
					itemList = itemrepository.findByMaxPrice(
							maxPrice, Sort.by(Sort.Direction.ASC, "price"));
					
					//「Query」で記述する場合
//					itemList = itemrepository.orderByPrice(sort);
				}
			}
		}
		
		else if(sort.equals("priceAsc")) {
				
			//Sortの機能を使う場合
			itemList = itemrepository.findAll(
					 Sort.by(Sort.Direction.ASC, "price"));
				
			//「Query」で記述する場合
			//itemList = itemrepository.orderByPrice(sort);
			
		}
		
		
		//キーワード
		else if (keyword != null && keyword.length() > 0) {
					//キーワードで絞り込んで取得
					itemList =itemrepository.findByKeyword("%"+ keyword +"%");
					
					//SQL上
						//SELECT * 
						//FROME items
						//WHERE name LIKE '% ～ %'
		}
				
		else {
			// DBから itemsテーブル のデータを全件取得   →「 .findAll()」
			itemList = itemrepository.findAll();
		}
		
//		 if(sort.equals("priceAsc")) {
//				
//			//Sortの機能を使う場合
//			itemList = itemrepository.findByMaxPrice(
//					maxPrice, Sort.by(Sort.Direction.ASC, "price"));
//				
//			//「Query」で記述する場合
//			//itemList = itemrepository.orderByPrice(sort);			
//		}
//		 else {
//				// DBから itemsテーブル のデータを全件取得   →「 .findAll()」
//				itemList = itemrepository.findAll();
//		}
//		
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("itemList", itemList);
		return "items";
	}

}
