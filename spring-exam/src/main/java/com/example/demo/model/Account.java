package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.User;

@SessionScope
@Component
public class Account {
	
	private String name;
	private String email;
	private String password;
	private List<User> userList = new ArrayList<>();
	
	
	public Account() {
	}
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public List<User> getUserList(){
		return userList;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
