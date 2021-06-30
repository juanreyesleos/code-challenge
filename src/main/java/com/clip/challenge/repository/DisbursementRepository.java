package com.clip.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clip.challenge.dto.DisbursementDTO;


@Repository
public interface DisbursementRepository  extends JpaRepository<DisbursementDTO, Integer>{
	
	@Query(value = "SELECT * FROM DISBURSEMENT  ORDER BY CLIP_USER,ID",nativeQuery = true)
	List<DisbursementDTO> findAllDisbursementDTOOrderByClipUserAndId();
	
}
