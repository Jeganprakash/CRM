package leadController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import leadDAO.DB;
import leadModel.LeadsData;

@WebServlet(name = "Update", urlPatterns = {"/Update"})
public class Update extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        System.out.println(req.getSession());
        String id = req.getSession().getAttribute("id").toString();
        String name = req.getParameter("editName");
        String phone = req.getParameter("editPhone");
        String email = req.getParameter("editEmail");
        String source = req.getParameter("editSource");
        
        System.out.println(id);
        System.out.println("UpdateLead");
        System.out.println(name+" "+phone+" "+email+" "+source+" "+id);
        DB obj = new DB();
        LeadsData data = new LeadsData(name, phone, email, source,id);
        boolean rf = false;
        try {
            rf = obj.updateLead(data);
        } catch (SQLException ex) {
            System.out.println("error"+ex);
        }
        if(rf){
            //UPDATE SUCCESSFULLY
            res.sendRedirect("Lead");
        }
    }
}
