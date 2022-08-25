package com.pack.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.dao.TransDao;
import com.pack.bank.model.Transaction;

@Service("transactionServiceImpl")
public class TransactionServiceImpl {
	
	@Autowired
	TransDao tdao;

	public int insert(Transaction a) {
		return tdao.insert(a);
	}

}
