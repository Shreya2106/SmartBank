package com.pack.Bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pack.Bank.model.Transaction;

public class TransDao {
	public int save(Transaction trans) {
	int status=0;
	try {
		Connection con=MySqlConn.getCon();
		PreparedStatement ps=con.prepareStatement("insert into transaction"
				+ "(accountNumber,transactionType,transactionDate,balance) "
				+ " values(?,?,?,?)");
		ps.setString(1,trans.getAccountNumber());
		ps.setString(2, trans.getTransactionType());
		ps.setString(3, trans.getTransactionDate());
		ps.setString(4, trans.getBalance());
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
	}
}
