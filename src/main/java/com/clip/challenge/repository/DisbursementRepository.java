package com.clip.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clip.challenge.entity.DisbursementEntity;


@Repository
public interface DisbursementRepository  extends JpaRepository<DisbursementEntity, Integer>{
	
	@Query(value = "SELECT * FROM DISBURSEMENTS  ORDER BY CLIP_USER,ID",nativeQuery = true)
	List<DisbursementEntity> findAllDisbursementDTOOrderByClipUserAndId();
	
}
