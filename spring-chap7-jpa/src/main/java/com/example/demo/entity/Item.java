package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// A5:SQLのソースからコピペすることで間違いを減らせる！

//	create table public.items (
//			  id serial not null
//			  , category_id integer
//			  , name text
//			  , price integer
//			  , primary key (id)　//一旦置いとく
//			);


@Entity                 //Entityクラスであることを宣言。DB接続に使えるようになる
@Table(name="items")    //接続するテーブル名を指定
public class Item {

	//フィールド
	@Id                              //主キー項目である事の宣言
	@GeneratedValue(strategy = GenerationType.IDENTITY)    //DBのテーブルの主キーが自動連番( Serial型、シーケンスがある)の場合、必要なアノテーション
	private Integer id;
	
	@Column(name="category_id")      //接続対象の項目を設定
	private Integer categoryId;
	
	@Column(name="name")             //接続対象の項目を設定   
	private String name;
	
	@Column(name="price")            //接続対象の項目を設定  
	private Integer price;
	
	//デフォルトコンストラクタ
	public Item() {
	}
	
	// id 以外のフィールドをすべて持つコンストラクタ(新規登録用)
	public Item(Integer categoryId, String name, Integer price) {
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}



	//getter/setter
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
