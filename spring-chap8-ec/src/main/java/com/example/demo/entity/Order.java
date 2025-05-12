package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;

//create table public.orders (
//		  id serial not null
//		  , customer_id integer
//		  , ordered_on date
//		  , total_price integer
//		  , primary key (id)
//		);


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="ordered_on")
	private LocalDate orderedOn;
	
	@Column(name="total_price")
	private Integer totalPrice;

	public Order() {
	}

	public Order(Integer customerId, LocalDate orderedOn, Integer totalPrice) {
		this.customerId = customerId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public LocalDate getOrderedOn() {
		return orderedOn;
	}

	public void setOrderedOn(LocalDate orderedOn) {
		this.orderedOn = orderedOn;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
