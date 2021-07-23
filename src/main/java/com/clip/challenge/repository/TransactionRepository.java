package com.clip.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clip.challenge.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
		
	List<TransactionEntity> findByClipUserOrderByClipUserAscIdAsc(@Param("clipUser") String clipUser);
		
	List<TransactionEntity> findByPaid(@Param("paid") Boolean paid);
	
	@Query(value= "UPDATE TRANSACTIONS  "
			+ "SET PAID=:paid , "
			+ " ID_DISBURSEMENT = :iddisbursement"
			+ " WHERE ID in :ids", nativeQuery = true)
	@Modifying
	void updatePaidAndId(@Param("paid") Boolean paid,@Param("ids") List<Integer> ids,@Param("iddisbursement") int iddisbursement);

}
