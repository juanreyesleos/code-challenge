package com.clip.challenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clip.challenge.dto.TransactionDTO;
import com.clip.challenge.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionRepository;
	
	 public List<TransactionDTO> getAllTransactions() {
			List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
			transactions= transactionRepository.findAll();
	        return transactions;
	    }

	    public List<TransactionDTO> getTransactionById(String  clipUser) {
	        return  transactionRepository.findByClipUserOrderByClipUser(clipUser);
	    }

	    public int save(TransactionDTO transaction) {
	    	transactionRepository.save(transaction);
	    	return transaction.getId();
	    }



}
