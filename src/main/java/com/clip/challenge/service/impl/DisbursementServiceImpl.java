package com.clip.challenge.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clip.challenge.dto.DisbursementDTO;
import com.clip.challenge.entity.DisbursementEntity;
import com.clip.challenge.entity.TransactionEntity;
import com.clip.challenge.repository.DisbursementRepository;
import com.clip.challenge.repository.TransactionRepository;
import com.clip.challenge.service.DisbursementService;

@Service
public class DisbursementServiceImpl implements DisbursementService{
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired	
	DisbursementRepository disbursementRepository;

	@Transactional
	public List<DisbursementDTO> makeDisbursement()  {		
		List<DisbursementDTO> disbursements= new ArrayList<DisbursementDTO>();
		Map<DisbursementEntity,List<TransactionEntity>> disbursementAndTransaction = new HashMap<>();
		List<TransactionEntity> transactions = transactionRepository.findByPaid(false);		
		
		Map<String, Double> totalAumountByUser  = transactions.stream().collect(
				Collectors.groupingBy(TransactionEntity::getClipUser, 
			    Collectors.summingDouble(TransactionEntity::getAmount)));

		totalAumountByUser.forEach((k, v) -> {
			List<TransactionEntity> filterByUser = transactions.stream().
					filter(
							t -> t.getClipUser().equals(k))
					.collect(Collectors.toList());

			DisbursementEntity disbursement = new DisbursementEntity(k, v);			
			disbursementAndTransaction.put(disbursement, filterByUser);
			disbursementRepository.save(disbursement);
			disbursements.add(convertToDto(disbursement));
			transactionRepository.updatePaidAndId(Boolean.TRUE, getListIds(filterByUser),disbursement.getId());			
		});		
		return disbursements;
	}
	
	public List<String> getAllDisbursement() {
		List<DisbursementEntity> findAllDisbursementDTOOrderByClipUserAndId = disbursementRepository.findAllDisbursementDTOOrderByClipUserAndId();
		List<String> disbursements =new  ArrayList<>();
		 AtomicInteger counter = new AtomicInteger(0);
		findAllDisbursementDTOOrderByClipUserAndId.forEach(d ->{
			System.out.println(d.getTransactions());
			disbursements.add( new String ("disbursement "+ counter.incrementAndGet()+": " + d.getTotalamount()+" pesos - "+d.getClipUser()+" - date: "+ formatDateToDdMmYy(d.getDate())   ));
		});			
	    return disbursements;
	}
	
	private DisbursementDTO convertToDto(DisbursementEntity disbursementEntity){
		DisbursementDTO disbursementDTO = new DisbursementDTO();
		disbursementDTO.setClipUser(disbursementEntity.getClipUser());
		disbursementDTO.setDate(disbursementEntity.getDate());
		disbursementDTO.setTotalamount(disbursementEntity.getTotalamount());
		disbursementDTO.setId(disbursementEntity.getId());		
		return disbursementDTO;
		
	}
	
	public List<Integer> getListIds(List<TransactionEntity> transactions) {
		List<Integer> ids = 
				transactions.stream()
			              .map( TransactionEntity::getId)
			              .collect(Collectors.toList());
		return ids;		
	}

	
	public String formatDateToDdMmYy(Timestamp timestamp) {
		String pattern = "dd/MM/yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateString = simpleDateFormat.format(timestamp);
		return dateString;
		
	}
	
	
	

}
