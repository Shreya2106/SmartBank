package com.pack.Bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pack.Bank.dao.MySqlConn;
import com.pack.Bank.model.Corporates;

public class CorporateDao {
	public int save(Corporates corp) {
		int status=0;
		try {
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("insert into corporate"
					+ "(corporateName,address,contactNumber)"
					+ "values(?,?,?);");
			ps.setString(1,corp.getCorporateName());
			ps.setString(2,corp.getAddress());
			ps.setString(3, corp.getContactNumber());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
		}
	public int delete(int id){
		int status=0;
		try{
			Connection con=MySqlConn.getCon();
			PreparedStatement ps=con.prepareStatement("update corporate set softdeleted=? where corporateId=?;");
			ps.setInt(1,1);
			ps.setInt(2,id);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}

		return status;
	}	
	public int update(Corporates u){
	int status=0;
	try{
		Connection con=MySqlConn.getCon();
		System.out.println(con);
		PreparedStatement ps=con.prepareStatement("update corporate set address=?,contactNumber=? where corporateId=?;");
		System.out.println("id= "+u.getCorporateId()+" "+u.getCorporateName()+" "+u.getAddress()+u);
		ps.setInt(3,u.getCorporateId());
		ps.setString(1,u.getAddress());
	 	ps.setString(2,u.getContactNumber());
		
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
   }
	
}
