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
@WebServlet("/TaskController")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TaskController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String resultMessage = "";
		JSONObject js=null;
		try {
		js=new JSONObject(request.getReader().readLine());
		String subject=js.getString("name");
		String content=js.getString("content");
		int assign=Integer.parseInt(js.getString("assign"));
		Connection con3=MysqLconnection.connect(DbCredentials.setDbDetails());
		PreparedStatement p=con3.prepareStatement("insert into t_tasks (adminpk,salespersonpk,task_title,task_content,is_finished) values (1,?,?,?,0)");
		p.setInt(1,assign);
		p.setString(2,subject);
		p.setString(3, content);
		p.executeUpdate();
		js.put("name",subject);
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
