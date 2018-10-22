package com.JBank.src.ControlerTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.JBank.src.authentication.Authentication;
import com.JBank.src.controllers.PortfolioController;
import com.JBank.src.model.Portfolio;
import com.JBank.src.model.Users;
import com.JBank.src.repositories.PortfolioRepository;
import com.JBank.src.repositories.StockPurchaseRepository;
import com.JBank.src.repositories.UsersRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(PortfolioController.class)
public class PortfolioControllerTest {
	
	@Autowired
	private MockMvc mockController;
	
	@MockBean
	private PortfolioRepository repository;
	
	@MockBean
	private StockPurchaseRepository purchaseRepository;
	
	@MockBean
	private UsersRepository usersRepository;
	
	private ObjectId testPortId;
	private ObjectId testOwnerId;
	private String testPrice = "100";
	private String testAmount = "5";
	private String testName = "Jack's incorporated";
	
	MockHttpServletResponse testResponse;
	

	private Portfolio mockPortfolio;
	
	@Mock
	private Users mockUser = mock(Users.class);

	@Before
	public void setUp() throws Exception {
		testPortId = new ObjectId();
		testOwnerId = new ObjectId();
		mockPortfolio = new Portfolio(testOwnerId);
		mockPortfolio.injectMockPurchaseRepository(purchaseRepository);
		Authentication.setRepositoryMocks(usersRepository, repository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getUserPorftolioReturnsMockUser() throws Exception {
		when(repository.findBy_ownerId(testOwnerId)).thenReturn(mockPortfolio);
		testResponse = mockController.perform(get("/portfolio/getPortfolio" + testOwnerId.toString()))
				.andExpect(status().isOk())
				.andReturn().getResponse();
		assertThat(testResponse.getContentAsString()).contains("balance");
	}
	
	// purchase tests
	
	@Test
	public void makeStockPurchaseValidReturnsUpdatedPortfolio() throws Exception {
		when(repository.findBy_id(testPortId)).thenReturn(mockPortfolio);
		when(usersRepository.findBy_id(testOwnerId)).thenReturn(mockUser);
		when(mockUser.get_id()).thenReturn(testOwnerId);
		when(repository.save(Matchers.any())).thenReturn(mockPortfolio);
		testResponse = mockController.perform(post("/portfolio/makePurchaseBuy?" +
												   "price=" + testPrice +
												   "&&amount=" + testAmount +
												   "&&stockName=" + testName +
												   "&&portId=" + testPortId.toString() +
												   "&&ownerId=" + testOwnerId.toString()))
				.andExpect(status().isOk())
				.andReturn().getResponse();
	}
	
	@Test
	public void makeStockPurchaseInValidReturnsErrorMessage() throws Exception {
		testResponse = mockController.perform(post("/portfolio/makePurchaseBuy?" +
												   "price=" + testPrice +
												   "&&amount=" + testAmount +
												   "&&stockName=" + testName +
												   "&&portId=" + testPortId.toString() +
												   "&&ownerId=" + testOwnerId.toString()))
				.andExpect(status().isNotAcceptable())
				.andReturn().getResponse();
		assertThat(testResponse.getContentAsString()).contains("Portfolio not found");
	}
}
