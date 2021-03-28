package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DbCredentials;
import util.MysqLconnection;

import java.sql.*;

@WebServlet("/integration")
public class AdminRegServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminRegServices() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname=request.getParameter("cname");
		String uname=request.getParameter("uname");
		String email=request.getParameter("email1");
		String password=request.getParameter("pass");
		email.toLowerCase();
		PrintWriter out=response.getWriter();
		try{
		
			java.sql.Connection cona = MysqLconnection.connect(DbCredentials.setDbDetails());
			Statement st=cona.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM crm.admin_accounts where email='"+email+"'");
			if(rs.next()){
				if(rs.getString("email").equals(email)){
					out.println("Id already exist..! Please Login.! <br><a href='login.html'>LOGIN</a>");
				}
			}
			else{
				st.executeUpdate("INSERT INTO `crm`.`admin_accounts` (`companyname`, `username`, `email`, `password`) VALUES ('"+cname+"', '"+uname+"', '"+email+"', '"+password+"')");
				out.println("Thank you for register ! Please <a href='login.html'>Login</a> to continue.");
			}
		}
		catch(Exception e){
			System.out.print(e);
			e.printStackTrace();
		}
		
	}
}