package com.pack.Bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pack.Bank.model.BackOfficeUser;

public class BackOfficeUserDao {

	public String getUserByEmail(String email){
		String pass="";
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select password from bank_login_cred where loginid=?");
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			rs.next();
			pass = rs.getString(1);
			System.out.println(pass);
			return pass;
		}
		catch(Exception e){System.out.println(e);
			return null;
		}
	}
	public String getUsername(String email) {
		String name="";
		BackOfficeUser u = null;
		try {
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select * from bank_login_cred where loginid=?");
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				u=new BackOfficeUser();
				u.setLoginId(rs.getString("loginId"));
				u.setPassword(rs.getString("password"));
				name=rs.getString("username");
				u.setUsername(name);
			}
		}catch(Exception e){System.out.println(e);}
		return name;
	}
}
