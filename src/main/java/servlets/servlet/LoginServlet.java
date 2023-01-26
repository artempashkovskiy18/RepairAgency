package servlets.servlet;

import constants.OtherConstants;
import models.User;
import service.UserService;
import servlets.CommonServletMethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = new UserService();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("remember-me");

        User user = service.getUserByEmail(email);

        if(user != null){
            if(user.getPassword().equals(password)){
                Cookie cookie = new Cookie("email", email);

                if(rememberMe == null){
                    cookie.setMaxAge(-1);
                    resp.addCookie(cookie);
                }else{
                    cookie.setMaxAge(OtherConstants.COOKIES_SAVING_TIME);
                    resp.addCookie(cookie);
                }
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }else{
                CommonServletMethods.forwardToErrorPage(req, resp, "wrong password");
            }
        }else{
            CommonServletMethods.forwardToErrorPage(req, resp, "wrong email");
        }


    }
}
