package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Categories;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoriesRepository;
import com.example.demo.repository.ItemRepository;



@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@GetMapping("/items")
	public String index(
			Model model) {
		
		List<Item> itemList = new ArrayList<>();
		itemList = itemRepository.findAll();
		
		model.addAttribute("itemList", itemList);
		return "items";
	}
	
	//新規登録リンクによる動作
	@GetMapping("/items/add")
	public String create() {
		return "addItem";
	}
	
	//新規登録html内の動作
	//＝＝ 登録 ＝＝
		//登録ボタンを押したとき、itemsテーブルにデータを登録する
		// ＊一連の流れ＊
			//1. 入力した内容の受け取り
			//2. エラーチェック
			//3. 入力した内容をDBに登録する
			//4. 商品一覧の画面を開く
				// (itemsテーブルのデータを取得して、items.htmlを開く)
	@PostMapping("/items/add")
	public String store(
			@RequestParam(name="categoryId") Integer categoryId,
			@RequestParam(name="name") String name,
			@RequestParam(name="price") Integer price
			) {
		
		//3.
		Item item = new Item(categoryId, name, price);
		itemRepository.save(item);
		
		//4.
		return "redirect:/items";
	}
	
	//更新用htmlへ
	//＝＝ 更新準備 ＝＝
	//更新ボタンを押したとき、更新のためのitemsテーブルにデータを表示する
	// ＊一連の流れ＊
		//1. 更新対象のデータを取得
		//2. 取得できなかった場合、商品一覧の画面に戻る
		//3. 取得したデータをhtmlで扱えるようにする
		//4. 更新画面を開く
	@GetMapping("/items/{id}/edit")
	public String edit(
			@PathVariable(name="id")Integer id,
			Model model) {
		
		//1.
		Optional<Item> dbData = itemRepository.findById(id);
		
		//2.
		if(dbData.isEmpty()) {
			return "redirect:/items";
		}
		
		//3.
		Item item = dbData.get();                  //　←
		
		//model.addAttribute("dbData", dbData);
		model.addAttribute("item", item);
		return "editItem";
	}
	
	
	//＝＝ 更新 ＝＝
	//更新ボタンを押したとき、itemsテーブルにデータを更新する
	// ＊一連の流れ＊
		//1. 更新対象のデータを取得
		//2. 更新予定のデータが存在するか確認
		//3. 取得できなかった場合、更新を行わずに、商品一覧の画面に戻る
				//1.idを入れたオブジェクトを作成する
				//  →DBから取得できたデータの特定のフィールドを書き換える
				//2. 更新を実施
		//4. DBのデータ更新を行う
		//5. 商品一覧の画面を開く(リダイレクト)
	@PostMapping("/items/{id}/edit")
	public String upodate(
			//パスパラ
			@PathVariable(name="id")Integer id,
			//リクエストパラメータ(入力フォーム)
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="price", defaultValue="") Integer price
			) {
		
		//1.
		Optional<Item> dbData = itemRepository.findById(id);
		
		//2.
		if(!dbData.isEmpty()){
			//3.
			Item item = dbData.get();                 
			item.setCategoryId(categoryId);
			item.setName(name);
			item.setPrice(price);	
			
			//4. 更新を実施
			itemRepository.save(item);
		}
		
		//5.
		return "redirect:/items";
	}
	
	//＝＝ 削除 ＝＝
	//削除ボタンを押したとき、itemsテーブルにデータを更新する
	// ＊一連の流れ＊
		//1. 更新対象のデータを取得
		//2. 更新予定のデータが存在するか確認
		//3. 取得できたらデータを削除
		//4. 商品一覧の画面を開く(リダイレクト)
	@PostMapping("/items/{id}/delete")
	public String delete(
			@PathVariable(name="id")Integer id
			) {
		
		//2.
		Optional<Item> dbData = itemRepository.findById(id);

		//3.
		if(!dbData.isEmpty()) {
			itemRepository.deleteById(id);
		}

		//4.
		return "redirect:/items";
	}
	
	
	
	
	
	
	
	
	@GetMapping("/categories")
	public String categoriesIndex(Model model) {
		
		List<Categories> categoryList = new ArrayList<>(); 
		categoryList = categoriesRepository.findAll();
		model.addAttribute("categoryList", categoryList);

		return "categories";
	}
	
	@GetMapping("/categories/add")
	public String categoriesCreate() {
		return "addCategories";
	}
	
	//＝＝ 登録 ＝＝
		//登録ボタンを押したとき、itemsテーブルにデータを登録する
		// ＊一連の流れ＊
			//1. 入力した内容の受け取り
			//2. エラーチェック
			//3. 入力した内容をDBに登録する
			//4. 商品一覧の画面を開く
				// (itemsテーブルのデータを取得して、items.htmlを開く)
	@PostMapping("/categories/add")
	public String categoriesStore(
			@RequestParam(name="name") String name
				) {
			//3.
			Categories categories = new Categories(name);
			categoriesRepository.save(categories);
			
			//4.
			return "redirect:/categories";
		}
	//＝＝ 更新準備 ＝＝
		//更新ボタンを押したとき、更新のためのitemsテーブルにデータを表示する
		// ＊一連の流れ＊
			//1. 更新対象のデータを取得
			//2. 取得できなかった場合、商品一覧の画面に戻る
			//3. 取得したデータをhtmlで扱えるようにする
			//4. 更新画面を開く
	@GetMapping("/categories/{id}/edit")
	public String categoriesEdit(
			@PathVariable(name="id")Integer id,
			Model model) {
			
		//1.
		Optional<Categories> dbData = categoriesRepository.findById(id);
	
		//2.
		if(dbData.isEmpty()) {
			return "redirect:/categories";
		}
			
		//3.
		Categories categories = dbData.get();
		model.addAttribute("categories", categories);
		return "editCategories";
	}
	
	
	//＝＝ 更新 ＝＝
		//更新ボタンを押したとき、itemsテーブルにデータを更新する
		// ＊一連の流れ＊
			//1. 更新対象のデータを取得
			//2. 更新予定のデータが存在するか確認
			//3. 取得できなかった場合、更新を行わずに、商品一覧の画面に戻る
					//1.idを入れたオブジェクトを作成する
					//  →DBから取得できたデータの特定のフィールドを書き換える
					//2. 更新を実施
			//4. DBのデータ更新を行う
			//5. 商品一覧の画面を開く(リダイレクト)
	@PostMapping("/category/{id}/edit")
	public String categorieUupodate(
			//パスパラ
			@PathVariable(name="id")Integer id,
			//リクエストパラメータ(入力フォーム)
			@RequestParam(name="name", defaultValue="") String name
			) {
			
			//1.
			Optional<Categories> dbData = categoriesRepository.findById(id);
			
			//2.
			if(!dbData.isEmpty()){
				//3.
				Categories categories = dbData.get();                 
				categories.setName(name);
				
				//4. 更新を実施
				categoriesRepository.save(categories);
			}
			
			//5.
			return "redirect:/categories";
		}
	
		
	//＝＝ 削除 ＝＝
	//削除ボタンを押したとき、itemsテーブルにデータを更新する
	// ＊一連の流れ＊
		//1. 更新対象のデータを取得
		//2. 更新予定のデータが存在するか確認
		//3. 取得できたらデータを削除
		//4. 商品一覧の画面を開く(リダイレクト)
	@PostMapping("/categories/{id}/delete")
	public String categoriesDelete(
			@PathVariable(name="id")Integer id
			) {
			
		//2.
		Optional<Categories> dbData = categoriesRepository.findById(id);

		//3.
		if(!dbData.isEmpty()) {
			categoriesRepository.deleteById(id);
		}

		//4.
		return "redirect:/categories";
	}
}
