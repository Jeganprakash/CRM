package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import services.*;
import util.ConvertJson;


@WebServlet("/getRfmData")
public class GetRfmDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public GetRfmDataController() {
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FetchRFMDataService cus  = new FetchRFMDataService();
		ResultSet rs=cus.getRFM();
		JSONArray js = new JSONArray();
		try {
			js = ConvertJson.convert(rs);
		} catch (Exception e) {
			System.out.println("GetRFMController");
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
