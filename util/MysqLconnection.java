package util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.DbModel;

import java.sql.*;
public class MysqLconnection {
	
	public static Connection connect(DbModel db) {
		System.out.println("in mysql conn");
		try {
		Class.forName("com.mysql.jdbc.Driver");
		String conUrl="jdbc:mysql://"+ db.host+":"+db.port+"/"+db.dbName;
		System.out.println(conUrl);
		Connection con=DriverManager.getConnection(conUrl,db.userName,db.password);   
		return con;
		}catch(Exception e){
			System.out.println(e);}  
		return null;
		}

	
	}
       
