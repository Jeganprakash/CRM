package models;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.sql.*;


public class ResultModel {

	public ArrayList<String> invoiceDate;
	public ArrayList<Double> totalAmount;
	public ArrayList<Integer> customerId;
	public ResultSet res;
	public JSONArray json;
	
	
	
		

}
