package com.pack.bank.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pack.bank.model.Transaction;

@Repository
public class TransDao {
	
	@Autowired 
	private DataSource dataSource;
	
	public int insert(Transaction t) { 	
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update("insert into transaction(accountNumber,transactionType,transactionDate,balance) values(?,?,?,?)",t.getAccountNumber(),t.getTransactionType(),t.getTransactionDate(),t.getBalance()); 
	} 

	public List<Transaction> viewAll() {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql="select * from users";    
	    return obj.query(sql,new BeanPropertyRowMapper<Transaction>(Transaction.class)); 
	} 
}
