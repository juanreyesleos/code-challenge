package com.clip.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clip.challenge.service.DisbursementService;

@RestController
public class DisbursementController {
	@Autowired
	DisbursementService disbursementService;
	
	@PostMapping("make/disbursement")
	public int saveDisbursement (DisbursementController disbursement) {
		disbursementService.makeDisbursement();
		return 1;
	}
	

}
