package com.pack.bank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pack.bank.controllers.LoginException;
import com.pack.bank.controllers.NoSuchUserException;
import com.pack.bank.model.BackOfficeUser;


@Repository
public class BackOfficeUserDao {
	
	JdbcTemplate jdbcTemplate;

	public BackOfficeUserDao(JdbcTemplate jdbcTemplate) {
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
	
	public BackOfficeUser getUserById(String id){    
		JdbcTemplate obj = new JdbcTemplate(dataSource);
	    String sql="select * from bank_login_cred where loginid=?";    
	    try{
	    	return obj.queryForObject(sql,new BeanPropertyRowMapper<BackOfficeUser>(BackOfficeUser.class), id); 
	    }
	    catch(EmptyResultDataAccessException e) {
	    	return null;
	    }
	}
	
	public boolean validateUser(String email, String password) {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql="select password from bank_login_cred where loginid='"+email+"'"; 
		try {
		List<String> strLst = obj.query(sql, new RowMapper<String>() {
		    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		        return rs.getString(1);
		    }
		});
		String pass= strLst.get(0);
	    if(pass.equals(password)) return true;
	    else 
	    	throw new LoginException("Invalid Credentials");
		}
		catch(IndexOutOfBoundsException e) {
			throw new NoSuchUserException("No such user");
	    }
	}
	
	public String getUsername(String email) {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql="select username from bank_login_cred where loginId='"+email+"'"; 
		List<String> strLst = obj.query(sql, new RowMapper<String>() {
		    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		        return rs.getString(1);
		    }
		});
		return strLst.get(0);
	}
}
