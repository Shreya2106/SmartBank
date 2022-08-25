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
import com.pack.bank.model.User;

@Repository
public class UserDao {
	
	JdbcTemplate jdbcTemplate;

	public UserDao(JdbcTemplate jdbcTemplate) {
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
	
	public User getUserById(String id){    
		JdbcTemplate obj = new JdbcTemplate(dataSource);
	    String sql="select * from users where loginId=?";    
	    try{
	    	return obj.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class), id); 
	    }
	    catch(EmptyResultDataAccessException e) {
	    	return null;
	    }
	}
	public int  modify(User u) {	 
		JdbcTemplate insert = new JdbcTemplate(dataSource);
	    return insert.update("update users set address='"+u.getAddress()+"',contactNumber='"+u.getContactNumber()+"' where loginId=\""+u.getLoginId()+"\"");	
	 } 
	
	 public int delete(String id) {			 	   	 	
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update("delete from users where loginId=\""+id+"\"");	 
	} 
	 public int insert(User u) { 	
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update("insert into users values(?,?,?,?,?,?,?,?)",u.getCorporateId(),u.getLoginId(),"userC123##",u.getUsername(),u.getDepartment(),u.getAddress(),u.getContactNumber(),0); 
	} 
	public int changePass(String loginid,String pass) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
	    return insert.update("update users set password=\""+pass+"\" , changed=1 where loginId=\""+loginid+"\"");
	}
	public int getStatus(String email) {
		String sql="select changed from users where loginId='"+email+"'";   
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		List<Integer> strLst = obj.query(sql, new RowMapper<Integer>() {
		    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		        return rs.getInt(1);
		    }
		});
		return strLst.get(0);
	}
	
	public boolean validateUser(String email, String password) {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql="select password from users where loginId='"+email+"'"; 
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
		String sql="select username from users where loginId='"+email+"'"; 
		List<String> strLst = obj.query(sql, new RowMapper<String>() {
		    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		        return rs.getString(1);
		    }
		});
		return strLst.get(0);
	}
	public List<User> viewAll() {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		String sql="select * from users";    
	    return obj.query(sql,new BeanPropertyRowMapper<User>(User.class)); 
	} 

}
