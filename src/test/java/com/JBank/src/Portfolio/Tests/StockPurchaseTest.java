package com.JBank.src.Portfolio.Tests;

import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.JBank.src.model.StockPurchase;

public class StockPurchaseTest {
	
	private StockPurchase testPurchase;
	private int testPrice = 255;
	private int testAmount = 500;
	private String testStockName = "Jack's incorporated";
	private Instant testDate;
	
	@Before
	public void setUp() throws Exception {
		testPurchase = new StockPurchase(testPrice, testAmount, testStockName);
		testDate = Instant.now();
	}

	@Test
	public void priceEqualsTestPrice() {
		assertEquals(testPrice, testPurchase.getPrice());
	}
	
	@Test
	public void amountEqualsTestAmount() {
		assertEquals(testAmount, testPurchase.getAmount());
	}
	
	@Test
	public void stockNameEqualsTestStockName() {
		assertEquals(testStockName, testPurchase.getStockName());
	}
	
	@Test
	public void purchaseDateEqualsTestDate() {
		assertEquals(testDate, testPurchase.getPurchaseDate());
	}
}
