package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.GetClustersService;

@WebServlet("/segment")
public class SegmentationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SegmentationController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/views/segment.jsp");
		System.out.println("integratecalled");
		GetClustersService clus = new GetClustersService();
		JSONObject js = clus.getCustomers();
		try {
			request.setAttribute("platinum", js.getInt("platinum"));
			request.setAttribute("gold", js.getInt("gold"));
			request.setAttribute("silver", js.getInt("silver"));
			request.setAttribute("bronze", js.getInt("bronze"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
