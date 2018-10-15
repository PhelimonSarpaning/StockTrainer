package com.JBank.src.authentication;

import java.util.regex.Pattern;

import com.JBank.src.model.Users;
import com.JBank.src.repositories.UsersRepository;

public class UsersAuthentication {
	
	private UsersAuthentication() { }
	
	private static UsersRepository repository;
	private static final Pattern emailPattern = Pattern.compile("[A-z0-9._%+-]+@[A-z.-]+\\.[A-z]{2,4}");
	private static final Pattern passwordPattern = Pattern.compile("[A-z]+[0-9]|[0-9]+[A-z]");
	// for mocking only
	
	public static void setRepository(UsersRepository injRepository) {
		repository = injRepository;
	}
	
	public static Users usernameExists(String username) {
		return repository.findByUsername(username);
	}
	
	public static boolean verifyPassword(String username, String password) {
		return repository.findByUsername(username).getPassword() == password;
	}
	
	public static boolean validEmail(String email) {
		return emailPattern.matcher(email).find();
	}
	
	public static boolean validPassword(String password) {
		return passwordPattern.matcher(password).find() && password.length() > 8;
	}
	
}
