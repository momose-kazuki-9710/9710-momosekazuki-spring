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
	
	//新規登録のリンクに飛んだ時、addItem.htmlを表示する
	@GetMapping("/items/add")
	public String create() {
		return "addItem";
	}
	

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
			
			//入力した内容の受け取り
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="price", defaultValue="") Integer price
//			Model model
			) {
		
		//エラーチェック
//	    try{
//	      //エラーが出る可能性のある処理
//	      }catch(Exception e){
//	      //エラーが出た時の対応
//	      }
		
		//入力した内容をDBに登録する
			//1. id が null のオブジェクトを作成
			//2. repository.save.オブジェクト(); を実行してデータ登録
		
		//1.
		Item item = new Item(categoryId, name, price);
		//2.
		itemrepository.save(item);
		
		//商品一覧の画面を開く
		// (itemsテーブルのデータを取得して、items.htmlを開く)
			// →GetMapping("/items")にアクセスさせる（リダイレクト）
	    		// →リダイレクト構文「return "redirect : Getリクエストのパス";」
		return "redirect:/items";
	}
	
	

	//＝＝ 更新 ＝＝
	//更新ボタンを押したとき、更新のためのitemsテーブルにデータを表示する
	// ＊一連の流れ＊
		//1. 更新対象のデータを取得
		//2. 取得できなかった場合、商品一覧の画面に戻る
		//3. 取得したデータをhtmlで扱えるようにする
		//4. 更新画面を開く

	
	//「localhost:8080/items/パスパラ/edit」にアクセスした時の処理
	@GetMapping("/items/{id}/edit")
	public String update(
			@PathVariable(name="id")Integer id,
			Model model) {
		
		//1. 更新対象のデータを取得
		//   →repository.findById(id);
		Optional<Item> dbData = itemrepository.findById(id);
		
		//2. 取得できなかった場合、商品一覧の画面に戻る
		if(dbData.isEmpty()) {
			//商品一覧画面にリダイレクト
			return "redirect:/items";
		}
		
		//3. 取得したデータをhtmlで扱えるようにする
		//Optional型からデータを取得
		Item item = dbData.get();
		model.addAttribute("item", item);
		
		//4. 更新画面( editItem.html )を開く
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
	public String update(
			//パスパラ
			@PathVariable(name="id")Integer id,
			//リクエストパラメータ(入力フォーム)
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="price", defaultValue="") Integer price
			) {
		
		//2.
		//  →取得できず更新しない  ⇒何もしない
		Optional<Item> dbData = itemrepository.findById(id);
		if(!dbData.isEmpty()) {
			//1.idを入れたオブジェクトを作成する
			//  →DBから取得できたデータの特定のフィールドを書き換える
			Item item = dbData.get();

			item.setCategoryId(categoryId);
			item.setName(name);
			item.setPrice(price);
			
			//2. 更新を実施
			itemrepository.save(item);
			
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
		Optional<Item> dbData = itemrepository.findById(id);

		//3.
		if(!dbData.isEmpty()) {
			itemrepository.deleteById(id);
		}

		//4.
		return "redirect:/items";
	}
}
