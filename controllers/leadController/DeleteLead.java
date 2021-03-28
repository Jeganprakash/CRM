package leadController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import leadDAO.DB;

@WebServlet(name = "DeleteLead", urlPatterns = {"/DeleteLead"})
public class DeleteLead extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        
        DB obj = new DB();
        
        obj.deleteLead(Integer.parseInt(id));
        
        res.sendRedirect("Lead");
    }
}
