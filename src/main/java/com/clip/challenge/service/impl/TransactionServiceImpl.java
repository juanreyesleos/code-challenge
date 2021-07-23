package com.clip.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clip.challenge.dto.DisbursementDTO;
import com.clip.challenge.dto.TransactionDTO;
import com.clip.challenge.entity.DisbursementEntity;
import com.clip.challenge.entity.TransactionEntity;
import com.clip.challenge.repository.TransactionRepository;
import com.clip.challenge.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transactionRepository;

	public TransactionDTO save(TransactionDTO transactionDTO ) {
		TransactionEntity transactionEntity = convertDto(transactionDTO);
		transactionRepository.save(transactionEntity);
		return convertDto(transactionEntity);
	}

	public List<TransactionDTO> findByClipUserOrderByClipUser(String clipUser) {
		List<TransactionEntity> transactioins = transactionRepository
				.findByClipUserOrderByClipUserAscIdAsc(clipUser);
		return convertDto(transactioins);
	}
	
	private TransactionEntity convertDto(TransactionDTO transactionDTO) {
		TransactionEntity transactionEntity = new TransactionEntity();
		transactionEntity.setAmount(transactionDTO.getAmount());
		transactionEntity.setClipUser(transactionDTO.getClipUser());
		transactionEntity.setCarddata(transactionDTO.getCarddata());		
		transactionEntity.setDate(transactionDTO.getDate());
		return transactionEntity;		
	}

	private List<TransactionDTO>  convertDto(List<TransactionEntity> transactions) {
		List<TransactionDTO> transactionsDto = new ArrayList<>();
		transactions.forEach(t -> {
			transactionsDto.add(convertDto(t));
		});
		return transactionsDto;
	}

	private TransactionDTO convertDto(TransactionEntity transactionEntity) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId(transactionEntity.getId());
		transactionDTO.setAmount(transactionEntity.getAmount());
		transactionDTO.setCarddata(transactionEntity.getCarddata());
		transactionDTO.setClipUser(transactionEntity.getClipUser());
		transactionDTO.setDate(transactionEntity.getDate());
		if (transactionEntity.getDisbursementEntity() != null) {
			transactionDTO.setDisbursement(
					convertDisbursementEntityToDisbursementDTO(transactionEntity.getDisbursementEntity()));
		}
		transactionDTO.setPaid(transactionEntity.isPaid());
		return transactionDTO;
	}

	public DisbursementDTO convertDisbursementEntityToDisbursementDTO(DisbursementEntity disbursementEntity) {
		DisbursementDTO disbursementDTO = new DisbursementDTO();
		disbursementDTO.setClipUser(disbursementEntity.getClipUser());
		disbursementDTO.setDate(disbursementEntity.getDate());
		disbursementDTO.setId(disbursementEntity.getId());
		disbursementDTO.setTotalamount(disbursementEntity.getTotalamount());
		return disbursementDTO;

	}

}
