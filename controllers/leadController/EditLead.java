package leadController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import leadModel.LeadsData;

@WebServlet(name = "EditLead", urlPatterns = {"/EditLead"})
public class EditLead extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        LeadsData o = SelectLead.get(id);
        request.getSession().setAttribute("leadsData", o);
        request.getRequestDispatcher("/WEB-INF/Update.jsp").forward(request, response);
    }

}
