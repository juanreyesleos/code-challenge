package com.clip.challenge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clip.challenge.model.Disbursement;
import com.clip.challenge.model.Transaction;
import com.clip.challenge.repository.DisbursementRepository;
import com.clip.challenge.repository.TransactionRepository;

@Service
public class DisbursementService {
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired	
	DisbursementRepository disbursementRepository;

	@Transactional
	public void makeDisbursement() {
		List<Disbursement> disbursements= new ArrayList<Disbursement>();
		Map<Disbursement,List<Transaction>> disbursementAndTransaction = new HashMap<>();
		List<Transaction> transactions = transactionRepository.findTransactionNoPaid();		
		
		Map<String, Double> totalAumountByUser  = transactions.stream().collect(
				Collectors.groupingBy(Transaction::getClipUser, 
			    Collectors.summingDouble(Transaction::getAmount)));

		totalAumountByUser.forEach((k, v) -> {
			List<Transaction> filterByUser = transactions.stream().
					filter(
							t -> t.getClipUser().equals(k))
					.collect(Collectors.toList());

			Disbursement disbursement = new Disbursement(k, v);
			disbursements.add(disbursement);
			disbursementAndTransaction.put(disbursement, filterByUser);
			disbursementRepository.save(disbursement);
			transactionRepository.updatePaidAndId(Boolean.TRUE, getListIds(filterByUser),disbursement.getId());			
		});		
	}
	
	public List<Integer> getListIds(List<Transaction> transactions) {
		List<Integer> ids = 
				transactions.stream()
			              .map( Transaction::getId)
			              .collect(Collectors.toList());
		return ids;		
	}

}
