package servlets.servlet;

import constants.Role;
import models.Order;
import models.User;
import service.OrderService;
import servlets.CommonServletMethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/change")
public class RedirectToOrderPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService service = new OrderService();
        int id = Integer.parseInt(req.getParameter("id"));
        Order order = service.getOrder(id);
        User user = CommonServletMethods.getUserFromCookies(req.getCookies());
        req.setAttribute("role", user.getRole());
        req.setAttribute("order", order);
        getServletContext().getRequestDispatcher("/order.jsp").forward(req, resp);
    }
}
