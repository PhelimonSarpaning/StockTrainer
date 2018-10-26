package com.JBank.src.PortfolioTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.JBank.src.model.Stock;

public class StockTest {
	
	private Stock testStock;
	
	private String testStockSymbol = "TestSym.1";
	private String testStockName = "TestName";
	private double testStockPrice = 150.00;
	private double testStockChange = 10.2;
	
	private double delta = 0.001;

	@Before
	public void setUp() throws Exception {
		testStock = new Stock(testStockSymbol, testStockName, testStockPrice, testStockChange);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// instance variable tests
	
	@Test
	public void getSymbolEqTestSymbol() {
		assertEquals(testStockSymbol, testStock.getSymbol());
	}
	
	@Test
	public void getNameEqTestName() {
		assertEquals(testStockName, testStock.getName());
	}
	
	@Test 
	public void getPriceEqTestPrice() {
		assertEquals(testStockPrice, testStock.getPrice(), delta);
	}
	
	@Test
	public void getChangeEqTestChange() {
		assertEquals(testStockChange, testStock.getChange(), delta);
	}

	

}
