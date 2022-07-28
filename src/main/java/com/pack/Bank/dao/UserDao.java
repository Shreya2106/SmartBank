package com.pack.Bank.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.pack.Bank.dao.MySqlConn;
import com.pack.Bank.model.Corporates;
import com.pack.Bank.model.User;

public class UserDao {
	public int getUserByEmail(String email, String password){
		int count = 0;
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select count(*) from users where loginId=? and password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			System.out.println(count);
		}
		catch(Exception e){System.out.println(e);}
		return count;
	}
	public int changePassword(String email,String password) {
		int status=0;
		try {
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("update users set password=?,changed=? where loginId=?");
			ps.setString(3,email);
			ps.setString(1,password);
			ps.setInt(2,1);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public String getUsername(String email) {
		String name="";
		User u = null;
		try {
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select * from users where loginId=?");
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				u=new User();
				u.setCorporateId(rs.getInt("corporateId"));
				u.setLoginId(rs.getString("loginId"));
				u.setPassword(rs.getString("password"));
				name=rs.getString("username");
				u.setUserName(name);
				u.setDepartment(rs.getString("department"));
				u.setAddress(rs.getString("address"));
				u.setContactNumber(rs.getString("contactNumber"));
				System.out.println(u.getCorporateId());
			}
		}catch(Exception e){System.out.println(e);}
		return name;
	}
	public int getChangedPasswordStatus(String email) {
		int status=0;
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select changed from users where loginId=?");
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			rs.next();
			status = rs.getInt(1);
			System.out.println(status);
		}catch(Exception e){System.out.println(e);}
		return status;	
	}
	public int save(User user) {
		int status=0;
		try {
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?);");
			ps.setInt(1,user.getCorporateId());
			ps.setString(2,user.getLoginId());
			ps.setString(3,user.getPassword());
			ps.setString(4,user.getUserName());
			ps.setString(5,user.getDepartment());
			ps.setString(6,user.getAddress());
			ps.setString(7,user.getContactNumber());
			ps.setInt(8,0);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
		}
	public int delete(String id){
		int status=0;
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("delete from users where loginId=?;");
			ps.setString(1,id);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}

		return status;
	}	
	public int update(User u){
	int status=0;
	try{
		Connection con=MySqlConn.getCon();
		System.out.println(con);
		PreparedStatement ps=con.prepareStatement("update users set address=?,contactNumber=? where loginId=?");
		System.out.println("id= "+u.getLoginId()+" "+u.getUserName()+" "+u.getAddress()+u);
		ps.setString(3,u.getLoginId());
		ps.setString(1,u.getAddress());
	 	ps.setString(2,u.getContactNumber());
		
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
   }
}
