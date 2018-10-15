package com.JBank.src;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JBank.src.model.Users;
import com.JBank.src.repositories.UsersRepository;


@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	UsersRepository repository;
	
	@RequestMapping("/getUsers")
	public List<Users> getUsers() {
		return repository.findAll();
	}	
	
	@RequestMapping("/{username}")
	public Users getUser(@PathVariable("username") String username) {
		return repository.findByUsername(username);
	}
}
