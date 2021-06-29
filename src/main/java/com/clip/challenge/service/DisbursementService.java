package com.clip.challenge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clip.challenge.dto.DisbursementDTO;
import com.clip.challenge.dto.TransactionDTO;
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
		List<DisbursementDTO> disbursements= new ArrayList<DisbursementDTO>();
		Map<DisbursementDTO,List<TransactionDTO>> disbursementAndTransaction = new HashMap<>();
		List<TransactionDTO> transactions = transactionRepository.findTransactionNoPaid();		
		
		Map<String, Double> totalAumountByUser  = transactions.stream().collect(
				Collectors.groupingBy(TransactionDTO::getClipUser, 
			    Collectors.summingDouble(TransactionDTO::getAmount)));

		totalAumountByUser.forEach((k, v) -> {
			List<TransactionDTO> filterByUser = transactions.stream().
					filter(
							t -> t.getClipUser().equals(k))
					.collect(Collectors.toList());

			DisbursementDTO disbursement = new DisbursementDTO(k, v);
			disbursements.add(disbursement);
			disbursementAndTransaction.put(disbursement, filterByUser);
			disbursementRepository.save(disbursement);
			transactionRepository.updatePaidAndId(Boolean.TRUE, getListIds(filterByUser),disbursement.getId());			
		});		
	}
	
	public List<Integer> getListIds(List<TransactionDTO> transactions) {
		List<Integer> ids = 
				transactions.stream()
			              .map( TransactionDTO::getId)
			              .collect(Collectors.toList());
		return ids;		
	}

}
