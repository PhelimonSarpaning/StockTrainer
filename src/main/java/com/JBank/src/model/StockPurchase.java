package com.JBank.src.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class StockPurchase {
	@Id
	public ObjectId _id;
	public int amount;
	
	public StockPurchase(int price, int amount, String stockName) {
		// TODO Auto-generated constructor stub
		amount = 100;
	}
	
	public ObjectId get_id() {
		return this._id;
	}
	
	public void setId(ObjectId _id) {
		this._id = _id;
	}
}
