package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.*;
import org.json.*;
import java.sql.*;
import util.*;

@WebServlet("/getCustomersTable")
public class GetCustomersData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetCustomersData() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FetchCustomersService cus  = new FetchCustomersService();
		ResultSet rs=cus.getCustomers();
		JSONArray js = new JSONArray();
		
		try {
			js = ConvertJson.convert(rs);
		} catch (Exception e) {
			System.out.println("GetCustomersController");
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        
        System.out.println(js);
        out.print(js);
        out.flush();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
