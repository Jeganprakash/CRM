package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import util.DbCredentials;
import util.MysqLconnection;

@WebServlet("/deletetodo")
public class deletetodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public deletetodo() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String resultMessage = "";
		JSONObject js=null;
		System.out.println("todo caled");
		try {
		js=new JSONObject(request.getReader().readLine());
		String subject=js.getString("obj");
		Connection con3=MysqLconnection.connect(DbCredentials.setDbDetails());
		PreparedStatement p=con3.prepareStatement("delete from t_todo where todo_name=? and personid=1;");
		p.setString(1,subject);
		p.executeUpdate();
		//js.put("hello", false);
		con3.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			PrintWriter out=response.getWriter();
		     response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
			out.print(js);
		}

	}

}
