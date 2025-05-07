package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//A5:SQLのソースからコピペすることで間違いを減らせる！

//	create table public.categories (
//			  id serial not null
//			  , name text
//			  , primary key (id)   //←主キーの項目を教えてくれている
//			);
//



@Entity                      //Entityクラスであることを宣言。DB接続に使えるようになる
@Table(name="categories")    //接続するテーブル名を指定
public class Categories {


	//フィールド
	@Id
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	
	//デフォルトコンストラクタ
	public Categories() {
	}

	
	//getter/setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
