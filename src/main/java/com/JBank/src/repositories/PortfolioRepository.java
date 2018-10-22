package com.JBank.src.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.JBank.src.model.Portfolio;

public interface PortfolioRepository  extends MongoRepository<Portfolio, String>{
	Portfolio findBy_id(ObjectId _id);	
	Portfolio findBy_ownerId(ObjectId _id);
}
