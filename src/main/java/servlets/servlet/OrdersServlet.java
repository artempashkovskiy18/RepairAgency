package servlets.servlet;

import constants.Role;
import models.Order;
import models.User;
import service.OrderService;
import servlets.CommonServletMethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        OrderService service = new OrderService();
        User user = CommonServletMethods.getUserFromCookies(cookies);


        if (user.getRole() != Role.MANAGER && user.getRole() != Role.CRAFTSMAN) {
            CommonServletMethods.forwardToErrorPage(req, resp, "you have no access to this page");
        } else {
            CommonServletMethods.forwardToOrders(req, resp, service, user);
        }
    }
}
