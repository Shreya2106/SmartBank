package com.pack.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.model.Transaction;
import com.pack.bank.repository.TransactionRepository;

@Service("transactionServiceImpl")
public class TransactionServiceImpl {
	
	@Autowired
	TransactionRepository tRepo;

	public void insert(Transaction a) {
		 tRepo.save(a);
	}

	public List<Transaction> viewDetails(int id) {
		return tRepo.findByAcNo(id);
	}

}
