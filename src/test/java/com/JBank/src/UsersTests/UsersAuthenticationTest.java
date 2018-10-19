package com.JBank.src.UsersTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import com.JBank.src.authentication.UsersAuthentication;
import com.JBank.src.model.Users;
import com.JBank.src.repositories.UsersRepository;

public class UsersAuthenticationTest {
	
	@Mock
	UsersRepository mockRepository = mock(UsersRepository.class);
	
	// sign up
	
	Users validLoginUser = new Users("jackIsCool", "1234qweasd", "validEmail@yahoo.com");
	Users testUser;
	
	private String validSignupUsername = "validUsername";
	private String validSignupPassword = "validPassword123";
	private String validSignUpEmail = "validEmail@yahoo.com";

	private String invalidSignupPassword = " bad pass";
	private String invalidSignUpEmail = "badEmail";
	
	// log in
	
	private String validLoginUsername = "jackIsCool";
	private String validLoginPassword = "1234qweasd";
	
	// uses valid sign-up details for invalid login
	
	// for exceptions
	
	private String expectedMessage;

	@Before
	public void setUp() throws Exception {
		UsersAuthentication.setRepository(mockRepository);
		when(mockRepository.findByUsername(validLoginUsername)).thenReturn(validLoginUser);
		when(mockRepository.findByUsername(validSignupUsername)).thenReturn(null);
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	//login tests
	
	@Test
	public void verifyUsernameExists() {
		testUser = UsersAuthentication.usernameExists(validLoginUsername);
		assertEquals(validLoginUser, testUser);
	}
	
	@Test
	public void verifyUsernameExistsFalse() {
		testUser = UsersAuthentication.usernameExists(validSignupUsername);
		assertEquals(null, testUser);
	}
	
	@Test
	public void verifyPasswordLogin() {
		assertTrue(UsersAuthentication.verifyPassword(validLoginUsername, validLoginPassword));
	}
	
	@Test
	public void verifyPasswordLoginInvalid() {
		assertFalse(UsersAuthentication.verifyPassword(validLoginUsername, validSignupPassword));
	}
	
	// sign-up tests
	
	@Test
	public void validEmailTrue() {
		assertTrue(UsersAuthentication.validEmail(validSignUpEmail));
	}
	
	@Test
	public void validEmailFalse() {
		assertFalse(UsersAuthentication.validEmail(invalidSignUpEmail));
	}
	
	@Test
	public void validPasswordTrue() {
		assertTrue(UsersAuthentication.validPassword(validSignupPassword));
	}
	
	@Test
	public void validPasswordFalse() {
		assertFalse(UsersAuthentication.validPassword(invalidSignupPassword));
	}
	
	// full cycle tests
	
	@Test
	public void verifySignUpSuccess() {
		try {
			UsersAuthentication.verifySignUp(validSignupUsername, validLoginPassword, validSignUpEmail);
		} catch(IllegalArgumentException e) {
			fail("Exception should not be thrown");
		}
	}
	
	@Test
	public void verifySignUpFail() {
		expectedMessage = "Username Taken";
		try {
			UsersAuthentication.verifySignUp(validLoginUsername, invalidSignupPassword, invalidSignUpEmail);
			fail("Exception should be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(expectedMessage, e.getMessage());
		}
	}
	
	@Test
	public void verifyLoginSuccess() {
		try {
			UsersAuthentication.verifyLogin(validLoginUsername, validLoginPassword);
		} catch(IllegalArgumentException e) {
			fail("Exception should not be thrown");
		}
	}
	
	@Test
	public void verifyLoginFail() {
		expectedMessage = "Invlaid Username or Password";
		try {
			UsersAuthentication.verifyLogin(validSignupUsername, invalidSignupPassword);
			fail("Exception should be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(expectedMessage, e.getMessage());
		}
	}
}
