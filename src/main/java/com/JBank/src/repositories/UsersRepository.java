package com.JBank.src.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.JBank.src.model.Users;

public interface UsersRepository extends MongoRepository<Users, String>{
	Users findBy_id(ObjectId _id);	
	Users findByUsername(String username);
}
