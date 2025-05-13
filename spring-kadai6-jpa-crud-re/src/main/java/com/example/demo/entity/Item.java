package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//create table public.items (
//		  id serial not null
//		  , category_id integer
//		  , name text
//		  , price integer
//		  , primary key (id)
//		);

@Entity
@Table(name="items")
public class Item {
	
	//フィールド
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="category_id")
	private Integer categoryId;
	@Column(name="name")
	private String name;
	@Column(name="price")
	private Integer price;
	
	
	//コントラスタ
	public Item() {
	}


	//コントラスタ（新規登録用）
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
