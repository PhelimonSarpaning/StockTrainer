package com.JBank.src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.JBank.src.repositories.StockPurchaseRepository;

public class Portfolio {
	
	@Id
	public ObjectId _id;
	public ObjectId _ownerId;
	public ArrayList<ObjectId> purchaseHistory;
	
	private int portfolioValue;
	private int balance;
	
	StockPurchaseRepository repository;
	
	public Portfolio(ObjectId ownerId) {
		this._ownerId = ownerId;
		this.purchaseHistory = new ArrayList<ObjectId>();
		this.portfolioValue = 0;
		this.balance = 0;
	}
	
	@Inject
	public void injectMockPurchaseRepository(StockPurchaseRepository mockRepository) {
		this.repository = mockRepository;
	}
	
	public Object getOwnerId() {
		return this._ownerId;
	}
	
	public void setOwnerId(ObjectId _ownerId) {
		this._ownerId = _ownerId;
	}
	
	public Object get_id() {
		return this._id;
	}
	
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public int getPortfolioValue() {
		return this.portfolioValue;
	}
	
	public ArrayList<ObjectId> getPurchaseHistory() {
		return this.purchaseHistory;
	}
	
	public void makeStockPurchase(int price, int amount, String stockName) {
		
		try {
			StockPurchase purchase = new StockPurchase(price, amount, stockName);
			purchase = repository.save(purchase);
			this.purchaseHistory.add(purchase.get_id());
			this.balance -= price * amount;
			this.portfolioValue += price * amount;
		} catch(NullPointerException e) {
			// todo error for repo problems
		}
		
	}
	
	public List<StockPurchase> getSavedPurchases() {
		return new ArrayList<>(this.purchaseHistory.stream()
				.map(i -> repository.findBy_id(i))
				.collect(Collectors.toList()));
	}
}
