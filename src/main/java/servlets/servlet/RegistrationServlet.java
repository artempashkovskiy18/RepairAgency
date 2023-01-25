package servlets.servlet;

import constants.OtherConstants;
import constants.Role;
import models.User;
import service.UserService;
import servlets.CommonServletMethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

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


        User user = new User(name, phone, email, password, Role.USER);
        if (service.getUserByEmail(email) == null && service.checkIfUserValid(user)) {
            Cookie emailCookie = new Cookie("email", email);
            resp.addCookie(emailCookie);

            if (rememberMe == null) {
                emailCookie.setMaxAge(-1);
            } else {
                emailCookie.setMaxAge(OtherConstants.COOKIES_SAVING_TIME);
            }

            service.addUser(user);
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else {
            System.out.println("Test");
            CommonServletMethods.forwardToErrorPage(req, resp, "you are already registered or entered data is invalid");
        }
    }
}
