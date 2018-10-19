package com.JBank.src.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JBank.src.repositories.StockPurchaseRepository;

@RestController
@RequestMapping("/purchase")
public class StockPurchaseController {
	
	@Autowired
	StockPurchaseRepository repository;
	
}
