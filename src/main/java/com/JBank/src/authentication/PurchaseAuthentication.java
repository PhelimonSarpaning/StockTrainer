package com.JBank.src.authentication;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.JBank.src.model.Portfolio;
import com.JBank.src.model.StockPurchase;

@Service
public class PurchaseAuthentication extends Authentication{
	
	private PurchaseAuthentication() { }
	
	public static void verifyStockPurchase(ObjectId portId, ObjectId userId, StockPurchase puchase) {
		if(portfolioExists(portId) == null) {
			throw new IllegalArgumentException("Portfolio not found");
		} else if(!portfolioValidOwnerId(portId, userId)) {
			throw new IllegalArgumentException("Invalid Credentials");
		}
	}
	
	private static Portfolio portfolioExists(ObjectId portId) {
		return portfolioRepository.findBy_id(portId);
	}
	
	private static boolean portfolioValidOwnerId(ObjectId portId, ObjectId userId) {
		if(usersRepository.findBy_id(userId) == null) {
			return false;
		}
		return portfolioExists(portId).getOwnerId().equals(usersRepository.findBy_id(userId).get_id());
	}
}
