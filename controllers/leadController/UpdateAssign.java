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

@WebServlet(name = "UpdateAssign", urlPatterns = {"/UpdateAssign"})
public class UpdateAssign extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lead_id = request.getParameter("lead_id_to_assign");
        int assign_to = Integer.parseInt(request.getParameter("lead_assign_value"));
        System.out.println(lead_id+"\n"+assign_to);
        LeadsData data = new LeadsData(lead_id,assign_to);
        DB obj = new DB();
        obj.addAssign(data);
        response.sendRedirect("Lead");
    }
}
