package com.JBank.src.model;

public class Stock {
	
	private String symbol;
	private String name;
	private double price;
	private double change;

	public Stock(String symbol, String name, double price, double change) {
		this.symbol = symbol;
		this.name = name;
		this.price = price;
		this.change = change;
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
