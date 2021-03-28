package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DbCredentials;
import util.MysqLconnection;

/**
 * Servlet implementation class FetchRFMDataService
 */
@WebServlet("/FetchRFMDataService")
public class FetchRFMDataService extends HttpServlet {
	public ResultSet getRFM() {
		int adminpk=1;
		ResultSet rs = null;
		Connection con = MysqLconnection.connect(DbCredentials.setDbDetails());
		try {
		PreparedStatement smt = con.prepareStatement("SELECT * from crm.rfm g JOIN (select distinct customersdata.customerId,customerName from crm.customersdata INNER JOIN crm.rfm ON customersdata.customerId = rfm.CustomerID) c ON c.customerId = g.CustomerID"); 
		 rs = smt.executeQuery();

		}catch(Exception e) {
			System.out.println("fetchRFMService: "+e);
		}
		
		return rs;
		
	}
}
