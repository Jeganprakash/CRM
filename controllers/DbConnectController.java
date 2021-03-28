package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import models.DbModel;

import services.*;

@WebServlet("/ConnectDB")
public class DbConnectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DbConnectController() {
        super();
     
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DbModel dbmodel=new DbModel();
		System.out.println("in connectDB");
		Gson reqJson = new Gson();
		dbmodel=reqJson.fromJson(request.getReader(), DbModel.class);
		

		
		ConnectionService con=new ConnectionService();
		
		System.out.println(dbmodel.database);
		boolean res = con.connectDB(dbmodel);
		
		
		 try {
			JSONObject sendRes = new JSONObject();
			
			
			Gson resJson = new Gson();
			String result=resJson.toJson(dbmodel);
		
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        sendRes.put("connection", res);
	        sendRes.put("dbModel", result);
	        
	        System.out.println(sendRes);
	        out.print(sendRes);
	        out.flush();
	        
	      } catch(Exception e) {
	    	System.out.print(res);
	      
	      }
		
		
		
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

}
}