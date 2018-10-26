package com.JBank.src.PortfolioTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import com.JBank.src.model.Portfolio;
import com.JBank.src.model.StockApiProcessor;
import com.JBank.src.model.StockPurchase;
import com.JBank.src.repositories.StockPurchaseRepository;
import com.JBank.src.repositories.UsersRepository;

public class PortfolioTest {
	
	private Portfolio testPortfolio;
	private ObjectId testOwnerId = new ObjectId();
	private ObjectId testPurchaseId = new ObjectId();
	// purchase variables
	
	private int testPurchasePrice = 100;
	private int testPurchaseAmount = 431;
	private String testPurchaseStockName = "Jack's Incorporated";
	
	private int expectedValue;
	private int expectedBalance;
	private int expectedCurrentValue;

	@Mock
	private StockPurchase mockPurchase =  mock(StockPurchase.class);
	
	@Mock
	private StockPurchaseRepository mockRepository = mock(StockPurchaseRepository.class);

	@Mock
	private StockApiProcessor mockStockApi = mock(StockApiProcessor.class);
	
	@Before
	public void setUp() throws Exception {
		testPortfolio = new Portfolio(testOwnerId);
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	// instance variables

	@Test
	public void getOwnerIdReturnsTestOwnerId() {
		assertEquals(testOwnerId, testPortfolio.getOwnerId());
	}
	
	@Test
	public void getPurchasesReturnsEmptySet() {
		assertTrue(testPortfolio.getPurchaseHistory().isEmpty());
	}
	
	@Test
	public void getBalanceReturnsZero() {
		assertEquals(0, testPortfolio.getBalance());
	}
	
	@Test
	public void getPurchaseValueReturnsZero() {
		assertEquals(0, testPortfolio.getPurchaseValue());
	}
	
	@Test
	public void getCurrentValueReturnsZero() {
		assertEquals(0, testPortfolio.getCurrentValue());
	}
	
	// single purchase tests
	
	private void makeStockPurchaseTest() {
		testPortfolio.injectMockPurchaseRepository(mockRepository);
		when(mockRepository.save(Matchers.any())).thenReturn(mockPurchase);
		when(mockRepository.findBy_id(Matchers.any())).thenReturn(mockPurchase);
		when(mockPurchase.get_id()).thenReturn(testPurchaseId);
		testPortfolio.makeStockPurchase(testPurchasePrice, testPurchaseAmount, testPurchaseStockName);
	}
	
	@Test
	public void makeStockPurchaseDecreasesBalance() {
		expectedBalance = - testPurchasePrice * testPurchaseAmount;
		makeStockPurchaseTest();
		assertEquals(expectedBalance, testPortfolio.getBalance());
	}
	
	@Test
	public void makeStockPurchaseIncreasesValue() {
		expectedValue = testPurchasePrice * testPurchaseAmount;
		makeStockPurchaseTest();
		assertEquals(expectedValue, testPortfolio.getPurchaseValue());
	}
	
	@Test
	public void makeStockPurchaseAddsToPurchaseHistory() {
		makeStockPurchaseTest();
		assertEquals(testPurchaseId, testPortfolio.getPurchaseHistory().get(0));
	}
	
	@Test
	public void  getSavedPurchasesReturnsListOfPuchaseObjs() {
		makeStockPurchaseTest();
		assertEquals(mockPurchase, testPortfolio.getSavedPurchases().get(0));
	}
	
	// api / profit tests
	// mocks take stock and halve their value
	
	private void setStockApiMock() {
		testPortfolio.injectMockStockApi(mockStockApi);
	}
	
	@Test
	public void getCurrentValueUsingMocks() {
		expectedValue = testPurchasePrice * testPurchaseAmount;
		expectedCurrentValue = expectedValue / 2;
		setStockApiMock();
		makeStockPurchaseTest();
		assertEquals(expectedCurrentValue, testPortfolio.getCurrentValue());
	}
}
