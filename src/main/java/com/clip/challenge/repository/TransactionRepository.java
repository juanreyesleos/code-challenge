package com.clip.challenge.repository;

import java.util.List;
import java.util.Map;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clip.challenge.dto.TransactionDTO;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDTO, Integer> {
	
	@Query( value ="SELECT * FROM TRANSACTION   WHERE PAID = FAL AND CLIP_USER= :clipUser ORDER BY CLIP_USER,ID ", nativeQuery =true)
	List<TransactionDTO> findByClipUserOrderByClipUser(@Param("clipUser") String clipUser);
	
	@Query( value ="SELECT * FROM TRANSACTION   WHERE PAID = FALSE ", nativeQuery =true)
	List<TransactionDTO> findTransactionNoPaid();
	
	@Query(value = "SELECT sum (amount) AS total ,clip_user FROM TRANSACTION group by CLIP_USER,ID", nativeQuery = true)
    List<Map<String, Object>> sumAmountByClipUser();
	

	@Query(value= "UPDATE TRANSACTION  "
			+ "SET PAID=:paid , "
			+ " ID_DISBURSEMENT = :iddisbursement"
			+ " WHERE ID in :ids", nativeQuery = true)
	@Modifying
	void updatePaidAndId(@Param("paid") Boolean paid,@Param("ids") List<Integer> ids,@Param("iddisbursement") int iddisbursement);

}
