package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DbCredentials;
import util.MysqLconnection;

@WebServlet("/Operations")
public class OperationsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OperationsController() {
        super();
  
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// reads form fields
		
		 Connection con1=MysqLconnection.connect(DbCredentials.setDbDetails());
		 int totaltasks=0;
	     int completed=0;
		 int pending=0;
		try {
			Statement st=con1.createStatement();
			ResultSet rs=st.executeQuery("select count(task_title) as count from t_tasks;");
			rs.next();
		    totaltasks=rs.getInt("count");
		    System.out.println(totaltasks);
			con1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con2=MysqLconnection.connect(DbCredentials.setDbDetails());
			Statement st=con2.createStatement();
			ResultSet rs=st.executeQuery("select count(task_title) as count from t_tasks where is_finished=0;");
			rs.next();
		    pending=rs.getInt("count");
		    completed=totaltasks-pending;
		    System.out.println(totaltasks);
			con2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con5=MysqLconnection.connect(DbCredentials.setDbDetails());
		String tasks="";
		try {
			Statement st=con5.createStatement();
			ResultSet rs=st.executeQuery("select task_title as task from t_tasks where is_finished=1;");
			while(rs.next())
			{
				tasks=tasks+rs.getString("task")+",";
			}
			System.out.println(tasks);
			con5.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con6=MysqLconnection.connect(DbCredentials.setDbDetails());
		String taskpen="";
		try {
			Statement st=con6.createStatement();
			ResultSet rs1=st.executeQuery("select task_title as task from t_tasks where is_finished=0;");
			while(rs1.next())
			{
				taskpen=taskpen+rs1.getString("task")+",";
			}
			System.out.println(taskpen);
			
			con6.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con4=MysqLconnection.connect(DbCredentials.setDbDetails());
		String todos="";
		try {
			Statement st=con4.createStatement();
			ResultSet rs=st.executeQuery("select todo_name as todo from t_todo where personid=1;");
			while(rs.next())
			{
				todos=todos+rs.getString("todo")+",";
			}
		    
		   // System.out.println("todos"+todos);
			con4.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("taskpending",taskpen);
		request.setAttribute("tasks",tasks);
		request.setAttribute("todos", todos);
		request.setAttribute("totaltask", totaltasks);
		request.setAttribute("completed",completed);
		request.setAttribute("pending", pending);
		RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/views/operations.jsp");
//		System.out.println("integratecalled");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
