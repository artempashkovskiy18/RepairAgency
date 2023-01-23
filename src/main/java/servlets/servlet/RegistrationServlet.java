package servlets.servlet;

import constants.OtherConstants;
import constants.Role;
import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//TODO at first comes mapping after that validation after that adding to cookies
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = new UserService();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("remember-me");
        String phone = req.getParameter("phone");

        Cookie emailCookie = new Cookie("email", email);


        if (service.checkIfUserExists(email)) {
            resp.sendRedirect("/error.jsp");
        } else if (rememberMe == null) {
            emailCookie.setMaxAge(-1);
            resp.addCookie(emailCookie);
        } else {
            emailCookie.setMaxAge(OtherConstants.COOKIES_SAVING_TIME);
            resp.addCookie(emailCookie);
        }

        service.addUser(new User(name, phone, email, password, Role.USER));
    }
}
