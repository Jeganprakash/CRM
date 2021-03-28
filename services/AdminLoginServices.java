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

@WebServlet("/integratenew")
public class AdminLoginServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLoginServices() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		email.toLowerCase();
		//session.putValue("email",email);
		String password=request.getParameter("pass");
		PrintWriter out=response.getWriter();
		
		try{
		
			java.sql.Connection conl = MysqLconnection.connect(DbCredentials.setDbDetails());
			Statement st= conl.createStatement();
			ResultSet rs=st.executeQuery("select * from crm.admin_accounts where email='"+email+"' and password='"+password+"'");
			rs.next();
			if(rs.getString("password").equals(password)&&rs.getString("email").equals(email)){
				response.sendRedirect("/CRM/integrate");
			}
			else{
				
				out.println("Invalid password or username.<a href='#'onclick='window.history.back()'>Back</a>");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}