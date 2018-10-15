package com.JBank.src.UsersTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Users;

public class UsersTest {
	
	private Users testUser;
	private String testUsername = "testuser";
	private String testPassword = "testPassword";
	private String testEmail = "testEmail";

	@Before
	public void setUp() throws Exception {
		testUser = new Users(testUsername, testPassword, testEmail);
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	// getters

	@Test
	public void usersTestUsername() {
		assertEquals(testUsername, testUser.getUsername());
	}
	
	@Test
	public void usersTestPassword() {
		assertEquals(testPassword, testUser.getPassword());
	}
	
	@Test
	public void usersTestEmail() {
		assertEquals(testEmail, testUser.getEmail());
	}
	
	// setters
	
	@Test
	public void usersSetUsername() {
		testUser.setUsername("newUsername");
		assertEquals("newUsername", testUser.getUsername());
	}
	
	@Test
	public void usersSetPassword() {
		testUser.setPassword("newPassword");
		assertEquals("newPassword", testUser.getPassword());
	}
	
	@Test
	public void usersSetEmail() {
		testUser.setEmail("newEmail");
		assertEquals("newEmail", testUser.getEmail());
	}

}
