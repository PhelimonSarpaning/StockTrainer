package com.JBank.src.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JBank.src.authentication.UsersAuthentication;
import com.JBank.src.model.Users;
import com.JBank.src.repositories.UsersRepository;


@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	UsersRepository repository;

	private Users createdUser;
	
	@RequestMapping("/getUsers")
	public List<Users> getUsers() {
		return this.repository.findAll();
	}	
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public Users getUser(@PathVariable("username") String username) {
		return this.repository.findByUsername(username);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email) {
		try {
			UsersAuthentication.verifySignUp(username, password, email);
			this.createdUser = this.repository.save(new Users(username, password, email));
			return new ResponseEntity<>(createdUser, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> loginUser(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		try {
			UsersAuthentication.verifyLogin(username, password);
			this.createdUser = this.repository.findByUsername(username);
			return new ResponseEntity<>(createdUser, HttpStatus.OK);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
