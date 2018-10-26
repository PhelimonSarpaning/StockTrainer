package com.JBank.src.model;

public class Stock {
	
	private String symbol;
	private String name;
	private double price;
	private double change;

	public Stock(String symbol, double price ) {
		this.symbol = symbol;
		this.price = price;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public double getChange() {
		return this.change;
	}
	
}
