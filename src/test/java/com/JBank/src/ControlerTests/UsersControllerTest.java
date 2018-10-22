package com.JBank.src.ControlerTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.JBank.src.authentication.UsersAuthentication;
import com.JBank.src.controllers.UsersController;
import com.JBank.src.model.Users;
import com.JBank.src.repositories.UsersRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {
	
	@Autowired
	private MockMvc mockController;
	
	@MockBean
	private UsersRepository repository;
	
	MockHttpServletResponse testResponse;
	
	private String testUsername = "jackIsCool";
	private String testPassword = "123qweasd112";
	private String testEmail = "testEmail@email.com";
	private Users testUser = new Users(testUsername, testPassword, testEmail);
	List<Users> testuserList = new ArrayList<Users>();
	
	private String responseError;

	@Before
	public void setUp() throws Exception {
		UsersAuthentication.setRepository(repository);
		testuserList.add(testUser);
	}
		
	@After
	public void tearDown() throws Exception {
	}
	
	// get user and users
	
	private void testUserAllAndSingle(String url) throws Exception {
		testResponse = mockController.perform(get(url).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andReturn().getResponse();
		assertThat(testResponse.getContentAsString()).contains(testUsername);
	}
		
	@Test
	public void getUsersReturnsSavedUsers() throws Exception {
		when(repository.findAll()).thenReturn(testuserList);
		testUserAllAndSingle("/user/getUsers");
	}
	
	@Test
	public void getUserByUsernameReturnstestUser() throws Exception {
		when(repository.findByUsername(Matchers.any())).thenReturn(testUser);
		testUserAllAndSingle("/user/jackIsCool");
	}
	
	// sign up
	
	@Test
	public void createReturnsUserWithValidAuth() throws Exception {
		testResponse = mockController.perform(post("/user/create?username=jackIsCool&&password=123qweasd112&&email=testEmail@email.com"))
				.andExpect(status().isOk())
				.andReturn().getResponse();
	}
	
	@Test
	public void createReturnsErrorWithInvalidAuth() throws Exception {
		responseError = "Username Taken";
		when(repository.findByUsername(Matchers.any())).thenReturn(testUser);
		testResponse = mockController.perform(post("/user/create?username=jackIsCool&&password=123qweasd112&&email=testEmail@email.com"))
				.andExpect(status().isNotAcceptable())
				.andReturn().getResponse();
		assertEquals(responseError, testResponse.getContentAsString());
	}
	
	// log in
	
	@Test
	public void loginReturnsUserWIthValidAuth() throws Exception {
		when(repository.findByUsername(Matchers.any())).thenReturn(testUser);
		testResponse = mockController.perform(post("/user/login?username=jackIsCool&&password=123qweasd112"))
				.andExpect(status().isOk())
				.andReturn().getResponse();
	}
	
	@Test
	public void loginReturnsErrorWithInvlaidAuth() throws Exception {
		responseError = "Invlaid Username or Password";
		when(repository.findByUsername(Matchers.any())).thenReturn(null);
		testResponse = mockController.perform(post("/user/login?username=jackIsCool&&password=123qweasd112"))
				.andExpect(status().isNotAcceptable())
				.andReturn().getResponse();
		assertEquals(responseError, testResponse.getContentAsString());
	}
	
	// update
	
	@Test
	public void updateChangesUserPassword() {
		
	}
	
	// delete
	
	@Test
	public void deleteRemovesUserAccount() {
		
	}

}
