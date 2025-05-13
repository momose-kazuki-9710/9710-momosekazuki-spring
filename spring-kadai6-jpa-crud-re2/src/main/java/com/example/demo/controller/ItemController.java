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

	@GetMapping("/items/add")
	public String create() {
		return "addItem";
	}
	
	
    //2.
    //＝＝ 登録 ＝＝
    //登録ボタンを押した時、itemsテーブルにデータを登録する
    // ＊一連の流れ＊
        //2.1. 入力した内容の受け取り
        //2.2. エラーチェック
        //2.3. 入力した内容をDBに登録する
        //2.4. 商品一覧の画面を開く
            // (itemsテーブルのデータを取得して、items.htmlを開く)　←リダイレクト
	
	@PostMapping("/items/add")
	public String store(
			@RequestParam(name="categoryId")Integer categoryId,
			@RequestParam(name="name")String name,
			@RequestParam(name="price")Integer price,
			Model model) {

		//2.3.
		Item item = new Item(categoryId, name, price);
		itemRepository.save(item);
		
		//2.4.
		return "redirect:/items";
	}
	
	
	
    //＝＝ 更新準備 ＝＝
    //更新ボタンを押したとき、更新のためのitemsテーブルにデータを表示する
    // ＊一連の流れ＊
        //1. 更新対象のデータを取得
        //2. 取得できなかった場合、商品一覧の画面に戻る
        //3. 取得したデータをhtmlで扱えるようにする
        //4. 更新画面を開く
	@GetMapping("/items/{id}/edit")
	public String edit(
			@PathVariable(name="id") Integer id,
			Model model) {
		
		//1.
		Optional<Item> dbDate = itemRepository.findById(id);
		
		//2.
		if(dbDate.isEmpty()) {
			return "redirect:/items";
		}
		
		//3.
		Item item = dbDate.get();
		model.addAttribute("item", item);
		
		//4.
		return "editItem";
	}

	
    //＝＝ 更新 ＝＝
    //更新ボタンを押したとき、itemsテーブルにデータを更新する
    // ＊一連の流れ＊
        //1. 更新対象のデータを取得
        //2. 更新予定のデータが存在するか確認
        //3. 取得できなかった場合、更新を行わずに、商品一覧の画面に戻る
                //3.1.idを入れたオブジェクトを作成する
                //  →DBから取得できたデータの特定のフィールドを書き換える
                //3.2. 更新を実施
        //4. DBのデータ更新を行う
        //5. 商品一覧の画面を開く(リダイレクト)

	@PostMapping("/items/{id}/edit")
	public String update(
			@PathVariable(name="id") Integer id,
			@RequestParam(name="categoryId") Integer categoryId,
			@RequestParam(name="name") String name,
			@RequestParam(name="price") Integer price,
			Model model) {
		
		//2.
		Optional<Item> dbDate = itemRepository.findById(id);
		
		//3.
		if(!dbDate.isEmpty()) {
			
			//3.1.
			Item item = dbDate.get();
			item.setCategoryId(categoryId);
			item.setName(name);
			item.setPrice(price);
			
			//3.2.
			itemRepository.save(item);
		}
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
			@PathVariable(name="id") Integer id) {
		
		//2.
		Optional<Item> dbDate = itemRepository.findById(id);
		
		//3.
		if(!dbDate.isEmpty()) {
			itemRepository.deleteById(id);
		}
		//4.
		return "redirect:/items";
	}
}

