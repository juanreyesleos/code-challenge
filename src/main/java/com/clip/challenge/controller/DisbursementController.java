package com.clip.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clip.challenge.dto.DisbursementDTO;
import com.clip.challenge.service.DisbursementService;

@RestController
public class DisbursementController {
	@Autowired
	DisbursementService disbursementService;
	
	@PostMapping("disbursement")
	public List<DisbursementDTO> saveDisbursement (DisbursementController disbursement) {
		return disbursementService.makeDisbursement();
	}
	
	@GetMapping("disbursement")
	public List<String> findByClipUserOrderByClipUser(){
		return  disbursementService.getAllDisbursement();
	} 
	
}