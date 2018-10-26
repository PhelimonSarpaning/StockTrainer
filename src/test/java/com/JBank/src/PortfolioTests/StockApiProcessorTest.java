package com.JBank.src.PortfolioTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.JBank.src.model.Stock;
import com.JBank.src.model.StockApiProcessor;

public class StockApiProcessorTest {
	
	private String testStockSym = "SNAP";
	private String expectedName = "ANGLO AMERICAN";
	
	private Stock[] testStockList;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getStockDataReturnsNewStock() throws IOException, ParseException {
		StockApiProcessor.getStockData(testStockSym);
		//assertEquals(expectedName, StockApiProcessor.getStockData(testStockSym));
	}
	
	@Test
	public void getAllStocksReturnsListOfStocks() throws IOException, ParseException {
		testStockList = StockApiProcessor.getAllStockData();
		
	}

}
