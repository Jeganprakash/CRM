package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DbCredentials;
import util.MysqLconnection;
import java.sql.*;

public class FetchCustomersService  {
	
       
	public ResultSet getCustomers() {
		int adminpk=1;
		ResultSet rs = null;
		Connection con = MysqLconnection.connect(DbCredentials.setDbDetails());
		try {
		PreparedStatement smt = con.prepareStatement("select * from customersdata where adminpk="+Integer.toString(adminpk));
		 rs = smt.executeQuery();

		}catch(Exception e) {
			System.out.println("fetchCustomerService: "+e);
		}
		
		return rs;
		
	}

}
