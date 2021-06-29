package com.clip.challenge.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.clip.challenge.model.Transaction;
import com.clip.challenge.service.TransactionService;

import ch.qos.logback.classic.Logger;

@RestController
public class TransactionController {
	protected Logger logger = (Logger) LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionServices;
	
	@PostMapping("/transaction")
	public int saveTransaction(@RequestBody Transaction transaction) {
		transaction.setDate(new Timestamp(System.currentTimeMillis()));
		return transactionServices.save(transaction);		
	}
	
	@GetMapping("/transaction/{clipUser}")
	public List<Transaction> getTransactionByClipUser(@PathVariable("clipUser") String clipUser) {
		return transactionServices.getTransactionById(clipUser);
	}

	
	@GetMapping("/transaction")
	public List<Transaction> getAllTransaction() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions = transactionServices.getAllTransactions();
		return transactions;
	}

	



}
