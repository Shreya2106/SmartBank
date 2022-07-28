package  com.pack.Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConn {

	static Connection c;

	public static Connection getCon()
{ 
	 

try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	if (c==null)
	{
 c=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartbank","root",
		 "#&AnaShree24##");
 System.out.println("connected "+c);
	}
	 
 
}

catch(Exception e)
{
	System.out.println(e);
}
return c;
}
}
