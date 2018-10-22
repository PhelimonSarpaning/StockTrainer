package com.JBank.src.PortfolioTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.JBank.src.authentication.Authentication;
import com.JBank.src.authentication.PurchaseAuthentication;
import com.JBank.src.model.Portfolio;
import com.JBank.src.model.StockPurchase;
import com.JBank.src.model.Users;
import com.JBank.src.repositories.PortfolioRepository;
import com.JBank.src.repositories.UsersRepository;

public class StockPurchaseAuthenticationTest {
	
	@Mock
	PortfolioRepository purchaseRepository = mock(PortfolioRepository.class);
	@Mock
	UsersRepository usersRepository = mock(UsersRepository.class);
	@Mock
	StockPurchase mockPurchase = mock(StockPurchase.class);
	@Mock
	Portfolio mockPortfolio = mock(Portfolio.class);
	@Mock
	Users mockUser = mock(Users.class);
	
	
	private ObjectId testPortfolioId = new ObjectId();
	private ObjectId testOwnerId = new ObjectId();
	private String expectedError;
	
	@Before
	public void setUp() throws Exception {
		Authentication.setRepositoryMocks(usersRepository, purchaseRepository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void verifyStockPurchasePassing() {
		when(purchaseRepository.findBy_id(testPortfolioId)).thenReturn(mockPortfolio);
		when(usersRepository.findBy_id(testOwnerId)).thenReturn(mockUser);
		when(mockUser.get_id()).thenReturn(testOwnerId);
		when(mockPortfolio.getOwnerId()).thenReturn(testOwnerId);
		try {
			PurchaseAuthentication.verifyStockPurchase(testPortfolioId, testOwnerId, mockPurchase);
		} catch(IllegalArgumentException e) {
			fail("exception should not be thrown " + e);
		}
		
	}
	
	@Test
	public void verifyStockPurchaseBadPortfolio() {
		expectedError = "Portfolio not found";
		try {
			PurchaseAuthentication.verifyStockPurchase(testPortfolioId, testOwnerId, mockPurchase);
			fail("exception should be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(expectedError, e.getMessage());
		}
	}
	
	@Test
	public void verifyStockPurchaseInvalidUser() {
		when(purchaseRepository.findBy_id(testPortfolioId)).thenReturn(mockPortfolio);
		expectedError = "Invalid Credentials";
		try {
			PurchaseAuthentication.verifyStockPurchase(testPortfolioId, testOwnerId, mockPurchase);
			fail("exception should be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(expectedError, e.getMessage());
		}
	}

}
