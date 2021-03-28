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

@WebServlet("/sendtodo")
public class sendtodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public sendtodo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultMessage = "";
		System.out.println("todo called");
		JSONObject js=null;
		try {
		js=new JSONObject(request.getReader().readLine());
		String subject=js.getString("name");
		String content=js.getString("content");
		Connection con3=MysqLconnection.connect(DbCredentials.setDbDetails());
		PreparedStatement p=con3.prepareStatement("insert into t_todo (todo_name,personid,todo_content) values (?,?,?);");
		p.setString(1,subject);
		p.setString(3,content);
		p.setInt(2,1);
		p.executeUpdate();
		js.put("name",subject);
		js.put("content",content);
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
