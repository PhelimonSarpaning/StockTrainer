package com.JBank.src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import com.JBank.src.repositories.StockPurchaseRepository;

public class Portfolio {
	
	@Id
	public ObjectId _id;
	public ObjectId _ownerId;
	public ArrayList<ObjectId> purchaseHistory;
	public String portfolioName;
	
	private int purchaseValue;
	private int currentValue;
	private int balance;
	
	@Autowired
	private StockPurchaseRepository repository;
	
	private StockApiProcessor mockStockApi;
	
	public Portfolio(ObjectId ownerId) {
		this._ownerId = ownerId;
		this.purchaseHistory = new ArrayList<ObjectId>();
		this.purchaseValue = 0;
		this.currentValue = 0;
		this.balance = 0;
	}
	
	@Inject
	public void injectMockPurchaseRepository(StockPurchaseRepository mockRepository) {
		this.repository = mockRepository;
	}
	
	@Inject
	public void injectMockStockApi(StockApiProcessor mockStockApi) {
		this.mockStockApi = mockStockApi;
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
	
	public int getPurchaseValue() {
		return this.purchaseValue;
	}
	
	public int caluclateCurrentValue() {
		return 0;
	}
	
	public int getCurrentValue() {
		return this.currentValue;
	}
	
	public int getPortfolioProfit() {
		caluclateCurrentValue();
		return this.currentValue - this.purchaseValue;
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
			this.purchaseValue += price * amount;
		} catch(NullPointerException e) {
			System.out.println(e.getMessage());
			// todo error for repo problems
		}
		
	}
	
	public List<StockPurchase> getSavedPurchases() {
		return new ArrayList<>(this.purchaseHistory.stream()
				.map(i -> repository.findBy_id(i))
				.collect(Collectors.toList()));
	}
}
