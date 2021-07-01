package com.clip.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clip.challenge.dto.TransactionDTO;
import com.clip.challenge.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionRepository;

	public List<TransactionDTO> findByClipUserOrderByClipUser(String clipUser) {
		return transactionRepository.findByClipUserOrderByClipUser(clipUser);
	}

	public TransactionDTO save(TransactionDTO transaction) {
		transactionRepository.save(transaction);
		return transaction;
	}

}
