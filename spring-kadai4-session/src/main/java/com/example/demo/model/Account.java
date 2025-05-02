package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope       //このクラスをセッション管理対象にする宣言
@Component          //このクラスを処理で扱いやすくする宣言　→@AutoWiredを使えるようにする
public class Account {
	
	private String name;

	public Account() {
	}

	public Account(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
