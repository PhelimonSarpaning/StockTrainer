package com.JBank.src.ControlerTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.JBank.src.authentication.UsersAuthentication;
import com.JBank.src.controllers.UsersController;
import com.JBank.src.model.Users;
import com.JBank.src.repositories.UsersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {
	
	@Autowired
	private UsersController testController;
	
	@Autowired
	@Mock
	UsersRepository mockRepository = mock(UsersRepository.class);
	
	@Autowired
	@Mock
	UsersAuthentication mockAuthentication = mock(UsersAuthentication.class);
	
	private Users testUser;
	private String testUsername = "testUsername";
	private String testPassword = "testPassword";
	private String testEmail = "testEmail";

	@Before
	public void setUp() throws Exception {
		testController.injectMocks(mockRepository, mockAuthentication);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private void passAuthentication() {
		
	}
	
	private void failAuthentcation() {
		
	}

	@Test
	public void contextLoads() {
		assertThat(testController).isNotNull();
	}

}
