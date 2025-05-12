package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//create table public.order_details (
//		  id serial not null
//		  , order_id integer
//		  , item_id integer
//		  , quantity integer
//		  , primary key (id)
//		);

@Entity
@Table(name="order_details")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="order_id")
	private Integer orderId;
	
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	

	public OrderDetail() {
	}

	public OrderDetail(Integer orderId, Integer itemId, Integer quantity) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	

}
