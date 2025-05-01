package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// DB と接続するための準備
@Entity                 //クラスがDBのテーブルと紐づくという言葉
@Table(name="items")    //この蔵sと紐づけるテーブルを設定
public class Item {
	
	//フィールド
	@Id
	private Integer id;
	
	@Column(name="category_id")
	private Integer categoryId;
	
 	private String name;
	private Integer price;
	
	
	//デフォルトコンストラクタ
	public Item() {
	}
	
	
	//引数有りのコンストラクタ
	public Item(String name, Integer price) {
		this.name = name;
		this.price = price;
	}
	
	public Item(Integer id, Integer categoryId, String name, Integer price) {
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}

	
	//getter,setter

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	

}
