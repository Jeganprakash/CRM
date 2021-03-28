package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import models.*;
import services.ConnectionService;


@WebServlet("/getData")
public class GetDbDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetDbDataController() {
        super();
     
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PostDataModel dbDetails=new PostDataModel();
		System.out.println("in getDataDB");
		Gson g = new Gson();
		
		 try {
	
			JSONArray param =  new JSONArray(request.getReader().readLine());
			System.out.println(param.getString(0));
			
			DbModel dbmodel = g.fromJson(param.getString(0), DbModel.class);
			DbFields dbFields = g.fromJson(param.getString(1), DbFields.class);
			
			ConnectionService con=new ConnectionService();
			
			System.out.println(dbmodel.dbName);
			boolean res = con.connectDB(dbmodel);
			
			boolean fetch=false;
			dbFields.adminpk=2;
			if(res==true) {
					
				 fetch =  con.fetchData(dbFields);
			}
			
			JSONObject sendRes = new JSONObject();
		
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        sendRes.put("action", fetch);
	        
	        System.out.println(sendRes);
	        out.print(sendRes);
	        out.flush();
	        
	      } catch(Exception e) {
	    	System.out.print("GetDbData");
	    	e.printStackTrace();
	      
	      }
		
		
		
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

}
}
	

class PostDataModel{
	public DbModel db;
	public DbFields df;
}
	
	
	