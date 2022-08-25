package com.pack.bank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pack.bank.model.Account;

@Repository
public class AccountDao {
	JdbcTemplate jdbcTemplate;

	public AccountDao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Autowired 
	private DataSource dataSource;

	public int insert(Account ac) { 	
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update("insert into accounts(corporateId,accountName,branch,currency,balance,active,openingBalance,closingBalance) values(?,?,?,?,?,?,?,?)",ac.getCorporateId(),ac.getAccountName(),ac.getBranch(),ac.getCurrency(),ac.getBalance(),0,ac.getOpeningBalance(),ac.getClosingBalance()); 
	} 
	public int delete(int id) {			 	   	 	
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update("update accounts set active=1 where accountNumber="+id+"");	 
	} 
	public String getBalanceFromAcNo(int acno) {
		String sql="select balance from accounts where accountNumber="+acno;   
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		List<String> strLst = obj.query(sql, new RowMapper<String>() {
		    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		        return rs.getString(1);
		    }
		});
		return strLst.get(0);
	}
	public int setBalanceAfterTrans(String amt, int acno) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update("update accounts set balance=\""+amt+"\" where accountNumber="+acno);
	}
	public Boolean checkACName(String acName,int id) {
		String sql="select count(*) from accounts where accountName='"+acName+"' and corporateId="+id;   
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		List<Integer> strLst = obj.query(sql, new RowMapper<Integer>() {
		    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		        return rs.getInt(1);
		    }
		});
		return (strLst.get(0)!=0);
	}
	public List<Account> viewAll() {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql="select * from accounts";    
	    return obj.query(sql,new BeanPropertyRowMapper<Account>(Account.class)); 
	} 
}
