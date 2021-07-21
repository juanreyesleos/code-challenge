package com.clip.challenge.service;

import java.util.List;

import com.clip.challenge.dto.TransactionDTO;

public interface  TransactionService {
	public List<TransactionDTO> findByClipUserOrderByClipUser(String clipUser);
	
	public TransactionDTO save(TransactionDTO transaction);

}
