package com.JBank.src.model;

import java.time.Instant;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class StockPurchase {
	@Id
	public ObjectId _id;
	public ObjectId portfolio_id;
	public int price;
	public int amount;
	public String stockName;
	public Instant purchaseDate;
	
	public StockPurchase(int price, int amount, String stockName) {
		this.price = price;
		this.amount = amount;
		this.stockName = stockName;
		this.purchaseDate = Instant.now();
	}
	
	public ObjectId get_id() {
		return this._id;
	}
	
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public String getStockName() {
		return this.stockName;
	}
	
	public Instant getPurchaseDate() {
		return this.purchaseDate;
	}
	
}
