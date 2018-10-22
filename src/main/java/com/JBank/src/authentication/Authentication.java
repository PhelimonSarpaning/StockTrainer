package com.JBank.src.authentication;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JBank.src.model.Users;
import com.JBank.src.repositories.PortfolioRepository;
import com.JBank.src.repositories.UsersRepository;

@Service
public class Authentication {
	
	protected Authentication() { }
	
	@Autowired
	protected static UsersRepository usersRepository;
	
	@Autowired
	protected static PortfolioRepository portfolioRepository;
	
	@Inject
	public static void setRepositoryMocks(UsersRepository mockUsers, PortfolioRepository mockPurchase) {
		usersRepository = mockUsers;
		portfolioRepository = mockPurchase;
	}
	
	public static Users usernameExists(String username) {
		return usersRepository.findByUsername(username);
	}
	
	public static void user_idExists(ObjectId user_id) {
		if(usersRepository.findBy_id(user_id) == null) {
			throw new IllegalArgumentException("User not found");
		}
	}
}
