package com.clip.challenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clip.challenge.model.Transaction;
import com.clip.challenge.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionRepository;
	
	 public List<Transaction> getAllTransactions() {
			List<Transaction> transactions = new ArrayList<Transaction>();
			transactions= transactionRepository.findAll();
	        return transactions;
	    }

	    public List<Transaction> getTransactionById(String  clipUser) {
	        return  transactionRepository.findByClipUserOrderByClipUser(clipUser);
	    }

	    public int save(Transaction transaction) {
	    	transactionRepository.save(transaction);
	    	return transaction.getId();
	    }



}
