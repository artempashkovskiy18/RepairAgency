package servlets.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String savePassword = req.getParameter("save-password");

        HttpSession session = req.getSession();
        String currentEmail = (String)session.getAttribute("email");
        String currentPassword = (String)session.getAttribute("password");

        if(currentEmail == null){
            session.setAttribute("email", email);
        }

        if(currentPassword == null){
            session.setAttribute("password", password);
        }

        PrintWriter out = resp.getWriter();
        out.println(currentEmail);
        out.println(currentPassword);
        out.println(savePassword);
    }
}
