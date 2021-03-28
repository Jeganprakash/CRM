package leadController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import leadDAO.DB;
import leadModel.LeadsData;

@WebServlet(name = "AddLeads", urlPatterns = {"/AddLeads"})
public class AddLeads extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String source = req.getParameter("source");
        
        DB obj = new DB();
        LeadsData data = new LeadsData(name, phone,email,source);
        
        obj.insertLead(data);
        PrintWriter out = res.getWriter();
        out.println("<script>");
        out.println("alert('Successfully Added')");
        out.println("</script>");
        res.sendRedirect("Lead");
    }
}
