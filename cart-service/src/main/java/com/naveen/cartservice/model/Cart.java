package com.naveen.cartservice.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="carts")
public class Cart {
	@Id
	private int id;
	private int userId;
	private Map<Integer, Item> items;
	private double total;
	
	public Cart() {}
	
	public Cart(int id, int userId, Map<Integer, Item> items, double total) {
		super();
		this.id = id;
		this.userId = userId;
		this.items = items;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<Integer, Item> getItems() {
		return items;
	}

	public void setItems(Map<Integer, Item> items) {
		this.items = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", items=" + items + ", total=" + total + "]";
	}
	
}
