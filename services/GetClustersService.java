package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import util.DbCredentials;
import util.MysqLconnection;
import java.sql.*;

public class GetClustersService  {
	
       
	public JSONObject getCustomers() {
		int adminpk=1;
		JSONObject js = null;
		ResultSet rs = null;
		Connection con = MysqLconnection.connect(DbCredentials.setDbDetails());
		try {
			PreparedStatement smtp = con.prepareStatement("SELECT count(customerName) as platinum from crm.rfm g JOIN (select distinct customersdata.customerId,customerName from crm.customersdata INNER JOIN crm.rfm ON customersdata.customerId = rfm.CustomerID) c ON c.customerId = g.CustomerID WHERE g.segment='Platinum'"); 
			 rs = smtp.executeQuery();
			 rs.next();
			 js = new JSONObject();
			 js.put("platinum", rs.getInt("platinum"));
			 
			 PreparedStatement smtg = con.prepareStatement("SELECT count(customerName) as gold from crm.rfm g JOIN (select distinct customersdata.customerId,customerName from crm.customersdata INNER JOIN crm.rfm ON customersdata.customerId = rfm.CustomerID) c ON c.customerId = g.CustomerID WHERE g.segment='Gold'"); 
			 rs = smtg.executeQuery();
			 rs.next();
			 js.put("gold", rs.getInt("gold"));
			 
			 PreparedStatement smts = con.prepareStatement("SELECT count(customerName) as silver from crm.rfm g JOIN (select distinct customersdata.customerId,customerName from crm.customersdata INNER JOIN crm.rfm ON customersdata.customerId = rfm.CustomerID) c ON c.customerId = g.CustomerID WHERE g.segment='Silver'"); 
			 rs = smts.executeQuery();
			 rs.next();
			 js.put("silver", rs.getInt("silver"));
			 
			 PreparedStatement smtb = con.prepareStatement("SELECT count(customerName) as bronze from crm.rfm g JOIN (select distinct customersdata.customerId,customerName from crm.customersdata INNER JOIN crm.rfm ON customersdata.customerId = rfm.CustomerID) c ON c.customerId = g.CustomerID WHERE g.segment='Bronze'"); 
			 rs = smtb.executeQuery();
			 rs.next();
			 js.put("bronze", rs.getInt("bronze"));

		}catch(Exception e) {
			System.out.println("GetClusterService: "+e);
		}
		
		return js;
		
	}

}
