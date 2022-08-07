package com.pack.Bank.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.pack.Bank.dao.MySqlConn;
import com.pack.Bank.model.*;

public class AccountDao {
	public int save(Account ac) {
		int status=0;
		try {
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("insert into accounts values(?,?,?,?,?,?,?,?,?,?);");
			ps.setInt(1,ac.getCorporateId());
			ps.setString(2,ac.getAccountNumber());
			ps.setString(3,ac.getAccountName());
			ps.setString(4,ac.getBranch());
			ps.setString(5,ac.getCurrency());
			ps.setString(6,ac.getBalance());
			ps.setInt(7,0);
			ps.setString(8,ac.getOpeningBalance());
			ps.setString(9,ac.getClosingBalance());
			ps.setString(10,ac.getCorporateName());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
		}
	public int delete(String id){
		int status=0;
		System.out.println(id);
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("update accounts set active=? where accountNumber=?;");
			ps.setInt(1,1);
			ps.setString(2,id);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}

		return status;
	}	
	public int getCorpId(String id) {
		int cid=0;
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select corporateId from accounts where accountNumber=?;");
			ps.setString(1,id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				cid = rs.getInt("corporateId");
			}
		}catch(Exception e){System.out.println(e);}

		return cid;
	}
	public String getBalanceFromAcNo(String acNo) {
		String bal="";
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select balance from accounts where accountNumber=?;");
			ps.setString(1,acNo);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				bal = rs.getString("balance");
			}
		}catch(Exception e){System.out.println(e);}

		return bal;
	}
	public int setBalanceAfterTrans(String bal,String acNo) {
		int status=0;
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("update accounts set balance=? where accountNumber=?;");
			ps.setString(1,bal);
			ps.setString(2,acNo);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public boolean checkACName(String acName) {
		boolean result=false;
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select count(*) from accounts where accountName=?;");
			ps.setString(1,acName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				if(rs.getInt(1)!=0) result = true;
				System.out.println(rs.getInt(1));
			}
		}catch(Exception e){System.out.println(e);}
		return result;
	}
	public boolean checkACNo(String acNo) {
		boolean result=false;
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("select count(*) from accounts where accountNumber=?;");
			ps.setString(1,acNo);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				if(rs.getInt(1)!=0) result = true;
				System.out.println(rs.getInt(1)); }
		}catch(Exception e){System.out.println(e);}
		return result;
	}
}
