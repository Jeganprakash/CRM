package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/sendmail")
public class sendmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String resultMessage = "";
		JSONObject js=null;
		try {
		String recipient = "aswinjr72@gmail.com jeganprakashb@gmail.com";
		js=new JSONObject(request.getReader().readLine());
		js.put("mail",false);
		String subject=js.getString("msg");
		String content=js.getString("msg1");
        String s[]=recipient.split(" ");
		
		
			for( String st : s)
			{
				EmailUtility.sendEmail(host, port, user, pass,st, subject,
						content);
				resultMessage = "The e-mail was sent successfully";
			}
		
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

    public sendmail() {
        super();

    }
	

}
