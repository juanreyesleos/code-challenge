package com.clip.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clip.challenge.model.Disbursement;


@Repository
public interface DisbursementRepository  extends JpaRepository<Disbursement, Integer>{
	


}
