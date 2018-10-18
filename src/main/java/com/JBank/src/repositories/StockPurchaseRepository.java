package com.JBank.src.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.JBank.src.model.StockPurchase;

public interface StockPurchaseRepository extends MongoRepository<StockPurchase, String> {
	StockPurchase findBy_id(ObjectId _id);	
}
