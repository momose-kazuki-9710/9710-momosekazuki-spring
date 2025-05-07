package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//create table public.items (
//		  id serial not null
//		  , category_id integer
//		  , name text
//		  , price integer
//		  , primary key (id)
//		);

@Entity //Entityクラスであることを宣言。DB接続に使えるようになる
@Table(name = "items") //接続するテーブル名を指定
public class Item {

	@Id
	private Integer id;

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Integer price;

	//デフォルトコンストラクタ
	public Item() {
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
