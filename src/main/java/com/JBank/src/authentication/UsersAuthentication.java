package com.JBank.src.authentication;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JBank.src.model.Users;
import com.JBank.src.repositories.UsersRepository;

@Service
public class UsersAuthentication {
	
	private UsersAuthentication() { }
	
	@Autowired
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
		return repository.findByUsername(username).getPassword().equals(password);
	}
	
	public static boolean validEmail(String email) {
		return emailPattern.matcher(email).find();
	}
	
	public static boolean validPassword(String password) {
		return passwordPattern.matcher(password).find() && password.length() > 8;
	}
	
	public static void verifySignUp(String username, String password, String email) {
		if(usernameExists(username) != null){
			throw new IllegalArgumentException("Username Taken");
		} else if(!validPassword(password)) {
			throw new IllegalArgumentException("Invalid Password");
		} else if(!validEmail(email)) {
			throw new IllegalArgumentException("Invalid Email");
		}
	}
	
	public static void verifyLogin(String username, String password) {
		if(usernameExists(username) == null || !verifyPassword(username, password)) {
			throw new IllegalArgumentException("Invlaid Username or Password");
		}
	}
	
}
