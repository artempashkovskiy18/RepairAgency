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

        if(cookies != null){

            User user = CommonServletMethods.getUserFromCookies(cookies);
            if(user != null){

                if(user.getRole() == Role.MANAGER || user.getRole() == Role.CRAFTSMAN){
                    List<Order> orders = service.getAllOrders();
                    req.setAttribute("orders", orders);
                    getServletContext().getRequestDispatcher("/orders.jsp").forward(req, resp);
                }else{
                    CommonServletMethods.forwardToErrorPage(req, resp, "you have no access to this page");
                }

            }else {
                CommonServletMethods.forwardToErrorPage(req, resp, "no such user. Try to log in");
            }

        }else{
            CommonServletMethods.forwardToErrorPage(req, resp, "you are not logged in");
        }
    }
}
