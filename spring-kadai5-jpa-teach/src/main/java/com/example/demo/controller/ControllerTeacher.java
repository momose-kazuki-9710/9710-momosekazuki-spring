package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Locale.Category;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	// itemsテーブルからデータを取得できるように
	// itemRepository(SQL等を記述できるファイル)を読み込む
	@Autowired
	ItemRepository itemRepository;

	// Step2追加：categoriesテーブルからデータを取得できるようにする
	@Autowired
	CategoryRepository categoryRepository;

	// Step9説明用：serviceクラス(処理を別メソッドに切り分けた時のクラス)はAutowiredで呼び出す
//	@Autowired
//	ItemService itemService;

	// [localhost:8080/items]にアクセスした時の処理
	@GetMapping("/items")
	public String index(
			// 検索パラメータ：パラメータ自体や値が存在しない可能性が高いので、defaultValueを絶対に入れる
			// Step2追加：カテゴリー検索できるように、クエリストリングでの検索パラメータを受け取る
			@RequestParam(name="categoryId", defaultValue = "") Integer categoryId,
			// Step3追加：〇〇円以下の検索パラメータを受け取る
			@RequestParam(name="maxPrice", defaultValue = "") Integer maxPrice,
			// Step4追加：ソート検索パラメータを受け取る
			@RequestParam(name="sort", defaultValue = "") String sort,
			// Step6追加：〇〇円以下の検索パラメータを受け取る
			@RequestParam(name="keyword", defaultValue = "") String keyword,
			// Step9説明用：検索条件用のクラスを別途切り出した場合、下記の記述のみで済む
//			@ModelAttribute ItemCondition condition,
			Model model) {

//		// Step1：itemsテーブルから全データ取得
//		List<Item> itemList = itemRepository.findAll();

		// Step2：categoryIdに応じてitemsテーブルからデータを取得させる→保存用の変数をifの前に定義する
		List<Item> itemList = new ArrayList<>();

		// categoryIdの指定が存在する(/items?categoryId=〇でアクセスした)場合
		if(categoryId != null) {
			// カテゴリーIDが一致するデータのみ取得する
			itemList = itemRepository.findByCategoryId(categoryId);
		}
		// Step7追加：maxPriceとキーワード二つの指定がある場合
		else if(maxPrice != null && keyword != null && !keyword.isEmpty()) {
			// ソート指定がある場合
			if(sort == null || sort.length() == 0) {
				// キーワードと最高金額で検索
				itemList = itemRepository.findByKeywordAndMaxPrice("%" + keyword + "%", maxPrice);
			}
			else {
				// 安い順の指定がある場合
				if(sort.equals("priceAsc")) {
					// キーワードと最高金額で検索して、ソートする
					itemList = itemRepository.findByKeywordAndMaxPrice(
							"%" + keyword + "%", 
							maxPrice, 
							Sort.by(Sort.Direction.ASC, "price"));

				}
			}

		}
		// Step3追加：maxPriceのみ指定が存在する場合
		else if(maxPrice != null) {
			// ソート指定がある場合
			if(sort == null || sort.length() == 0) {
				// 〇〇円以下で取得する
				itemList = itemRepository.findByMaxPrice(maxPrice);
			}
			else {
				// 安い順の指定がある場合
				if(sort.equals("priceAsc")) {
					// キーワードと最高金額で検索して、ソートする
					itemList = itemRepository.findByMaxPrice(
							maxPrice, 
							Sort.by(Sort.Direction.ASC, "price"));

				}
			}
		}
		// Step6追加：keywordの指定が存在する場合
		else if(keyword != null && keyword.length() > 0) {
			// ソート指定がある場合
			if(sort == null || sort.length() == 0) {
				// キーワードで取得する
				itemList = itemRepository.findByKeyword("%" + keyword + "%");
			}
			else {
				// 安い順の指定がある場合
				if(sort.equals("priceAsc")) {
					// キーワードと最高金額で検索して、ソートする
					itemList = itemRepository.findByKeyword(
							"%" + keyword + "%", 
							Sort.by(Sort.Direction.ASC, "price"));

				}
			}
		}
		// Step4追加：ソートのみ指定が存在する場合
		else if(sort != null && sort.length() > 0) {
			// priceAscでソート指定されている場合、安い順で返却する
			if(sort.equals("priceAsc")) {
				itemList = itemRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
			}
		}
		// 検索の指定が存在しない(/itemsのみでアクセスした)場合
		else {
			// itemsテーブルから全データ取得
			itemList = itemRepository.findAll();
		}


		// Step9の説明用：こちらを実装した場合、上記までのものはコメントアウトする(不必要になるため)
//		itemList = itemService.getItemsByCondition(condition);

		// items.htmlで取得したデータを扱えるように登録
		model.addAttribute("itemList", itemList);

		// Step2追加: カテゴリーテーブルから全データ取得
		List<Category> categoryList = categoryRepository.findAll();
		// Step2追加: items.htmlで取得したデータを扱えるように登録
		model.addAttribute("categoryList", categoryList);

		// Step5追加：maxPriceの指定を保持できるようにする
		model.addAttribute("maxPrice", maxPrice);
		// Step6追加：keywordの指定を保持できるようにする
		model.addAttribute("keyword", keyword);

		// items.htmlを表示する
		return "items";
	}
}