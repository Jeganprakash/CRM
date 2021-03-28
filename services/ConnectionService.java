package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import models.*;
import util.MysqLconnection;
import util.*;

import java.sql.*;


	public class ConnectionService{
		
		public Connection con;
		
		public boolean connectDB(DbModel db) {
			if(db.database.equals("mysql")) {
				con=MysqLconnection.connect(db);
				System.out.println("hi");
				if(con==null) {
					return false;
				}		
			}
			
			return true;
			
		}
		
		
		public boolean fetchData(DbFields db) {
			ResultModel resMod =  new ResultModel();
			System.out.println(db.customerEmail);
			
			try {
			PreparedStatement stmt=con.prepareStatement("select * from "+db.tableName+" LIMIT 0,1000"); 
//			stmt.setString(1, db.customerName);
//			stmt.setString(2,db.customerEmail);
//			stmt.setString(3,db.invoiceDate);
//			stmt.setString(4, db.itemName);
//			stmt.setString(5, db.customerId);
//			stmt.setString(6,db.totalAmount);
//			stmt.setString(7, db.quantity);
//			stmt.setString(8, db.unitPrice);
			
			
			ResultSet Res = stmt.executeQuery();
			
			resMod.res=Res;
			
			
			//getting my db credentials
			DbModel dbmodel = DbCredentials.setDbDetails();
			
			//establishing mysql connection
			Connection conto=MysqLconnection.connect(dbmodel);
			PreparedStatement stmtto=conto.prepareStatement("insert into "+dbmodel.tableName+" (customerName,customersEmail,invoiceDate,itemName,customerId,totalAmount,quantity,unitPrice,adminpk) values (?,?,?,?,?,?,?,?,?)");
			
			while(Res.next()) {
				
				stmtto.setString(1, Res.getString(db.customerName));
				stmtto.setString(2, Res.getString(db.customerEmail));
				stmtto.setString(3, Res.getString(db.invoiceDate));
				stmtto.setString(4,  Res.getString(db.itemName));
				stmtto.setInt(5, Res.getInt(db.customerId));
				stmtto.setDouble(6, Res.getDouble(db.totalAmount));
				stmtto.setInt(7, Res.getInt( db.quantity));
				stmtto.setDouble(8, Res.getDouble(db.unitPrice));
				stmtto.setInt(9,db.adminpk);
		
				System.out.println(db.customerName+"hi ");
				stmtto.executeUpdate();
				
			}
			
			con.close();
			conto.close();
			
			}catch(Exception e) {
				
				System.out.println("ConnectionService");
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		
	       

	}

       