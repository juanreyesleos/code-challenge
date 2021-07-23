package com.clip.challenge.service;

import java.util.List;

import com.clip.challenge.dto.DisbursementDTO;

public interface DisbursementService {

	public List<DisbursementDTO> makeDisbursement();

	public List<String> getAllDisbursement();

}
