package com.pack.bank.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pack.bank.model.Corporates;

@Repository
public class CorporateDao {
	
	@Autowired 
	private DataSource dataSource;
	
	public Corporates getCorpById(int id){    
		JdbcTemplate obj = new JdbcTemplate(dataSource);
	    String sql="select * from corporate where corporateId=?"; 
	    try {
	    	return obj.queryForObject(sql, new BeanPropertyRowMapper<Corporates>(Corporates.class), id);  
	    }
	    catch(EmptyResultDataAccessException e) {
	    	return null;
	    }
	 } 
	
	 public int  modify(Corporates c) {	 
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return	insert.update("update corporate set address='"+c.getAddress()+"',contactNumber='"+c.getContactNumber()+"' where corporateId="+c.getCorporateId());
	 } 
	
	 public int delete(int id) {			 	   	 	
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update("update corporate set softdeleted=1 where corporateId="+id);	 
	} 
	 public int insert(Corporates c) { 	
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update("insert into corporate(corporateName,address,contactNumber)values(?,?,?)",c.getCorporateName(),c.getAddress(),c.getContactNumber());
	}

	public List<Corporates> viewAll() {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql="select * from corporate";    
	    return obj.query(sql,new BeanPropertyRowMapper<Corporates>(Corporates.class)); 
	} 
}
