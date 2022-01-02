package com.naveen.orderservice.model;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="orders")
public class Order {
	
	@Id
	private int id;
	private int customerId;
	private long amount;
	private Map<Integer, Item> items;
	private LocalDate date;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public Map<Integer, Item> getItems() {
		return items;
	}
	public void setItems(Map<Integer, Item> items) {
		this.items = items;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", amount=" + amount + ", items=" + items + ", date="
				+ date + ", status=" + status + "]";
	}
	
	
}
