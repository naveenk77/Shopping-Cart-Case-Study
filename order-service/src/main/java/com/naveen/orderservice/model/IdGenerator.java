package com.naveen.orderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="orderId")
public class IdGenerator {
	
	@Id
	String id;
	int seq;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "IdGenerator [id=" + id + ", seq=" + seq + "]";
	}
	
	
}
