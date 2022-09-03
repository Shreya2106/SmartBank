package com.pack.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pack.bank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

	@Query(value="select * from transaction where account_number=:id",nativeQuery = true)
	List<Transaction> findByAcNo(int id);

}
