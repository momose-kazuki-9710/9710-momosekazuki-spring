package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope       //このクラスをセッション管理対象にする宣言
@Component          //このクラスを処理で扱いやすくする宣言　→@AutoWiredを使えるようにする

public class Cart {
	
	//Listのフィールド
	private List<Item> items = new ArrayList<>();
	
	//ゲッター
	public List<Item> getItems(){
		return items;
	}

}
