package com.JBank.src.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JBank.src.authentication.PurchaseAuthentication;
import com.JBank.src.model.Portfolio;
import com.JBank.src.repositories.PortfolioRepository;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
	
	@Autowired
	PortfolioRepository repository;
	
	private Portfolio portfolio;
	
	// non ownership actions
	
	@RequestMapping(value = "/getPortfolio{user_id}", method = RequestMethod.GET)
	public Portfolio getUserPortfolio(@PathVariable("user_id") String user_id) {
		return repository.findBy_ownerId(new ObjectId(user_id));
	}
	
	// ownership actions
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<Object> createPortfolio(@RequestParam("user_id") String user_id) {
		try {
			PurchaseAuthentication.user_idExists(new ObjectId(user_id));
			portfolio = repository.save(new Portfolio(new ObjectId(user_id)));
			return new ResponseEntity<>(portfolio, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value="/makePurchaseBuy", method = RequestMethod.POST)
	public ResponseEntity<Object> makeStockPurchase(
			@RequestParam("price") String price,
			@RequestParam("amount") String amount,
			@RequestParam("stockName") String stockName,
			@RequestParam("portId") String portId,
			@RequestParam("ownerId") String ownerId
			){
		try {
			PurchaseAuthentication.verifyStockPurchase(new ObjectId(portId), new ObjectId(ownerId), null);
			portfolio = repository.findBy_id(new ObjectId(portId));
			portfolio.makeStockPurchase(Integer.valueOf(price), Integer.valueOf(amount), stockName);
			return new ResponseEntity<>(repository.save(portfolio), HttpStatus.OK);
		
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
}
