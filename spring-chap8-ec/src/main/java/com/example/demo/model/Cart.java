package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

@SessionScope
@Component

public class Cart {

	//Listのフィールド
	private List<Item> itemList = new ArrayList<>();

	public Cart() {
	}

	//getterのみでよい
	public List<Item> getItemList() {
		return itemList;
	}
	
	
	//合計金額取得用
	/**
	 * カート内の商品の合計金額を算出して返却する処理
	 * @return
	 */
	public Integer getTotalPrice() {
		Integer total = 0;
		for(Item item : itemList) {
			total += item.getPrice() * item.getQuantity();
		}
		return total;
	} 
	
	

	//メソッド
	//処理概要：引数に渡された商品をカートに追加
	//処理内容：1. 引数が null →処理終了
	//             null でない →フィールドのitemListに追加
	//          2. フィールドitemList に商品がすでにある場合 →個数を追加
	//             商品がない場合 →itemを追加

	public void add(Item item) {

		//1.
		if (item == null) {
			return;
		}

		//2.
		//フィールドitemListの中に、引数のitem.idと同じidを持つデータがあるかどうか
		//保存用変数を作成
		Item existItem = null;

		//拡張for文を実施
		// ↑フィールドitemListの中身を1つずつ確認するため
		for (Item cartItem : itemList) {

			//追加しようとしているitemIdとカート内の商品のitemIdが一致した場合
			if (item.getId() == cartItem.getId()) {

				// existItem に cartItem を入れて保存
				existItem = cartItem;

				// for 文を抜け出す
				break;
			}
		}

		//フィールドitemList に商品がすでにある場合 →個数を追加
		if (existItem != null) {
			existItem.setQuantity(existItem.getQuantity() + 1);

		}

		//商品がない場合 →itemを追加
		else {
			//個数を1にする
			item.setQuantity(1);

			//nullじゃない場合はフィールドitemListに追加
			itemList.add(item);
		}

		//		itemList.add(item);
	}

	/**
	 * 引数で指定された商品を削除する処理
	 * @param cartIdDelete
	 */
	public void delete(Integer cartIdDelete) {
		//1.
		//フィールドitemListの中に、引数のitem.idと同じidを持つデータがあるかどうか
		//保存用変数を作成
		//Item existItem = null;

		//拡張for文を実施
		// ↑フィールドitemListの中身を1つずつ確認するため
		for (Item cartItem : itemList) {
			//追加しようとしているitemIdとカート内の商品のitemIdが一致した場合
			if (cartItem.getId() == cartIdDelete) {

				// existItem に cartItem を入れて保存
				itemList.remove(cartItem);

				// for 文を抜け出す
				break;
			}
		}
	
	}
	
	public void clear() {
		itemList = new ArrayList<>();
	}
	

}

