package servlets.servlet;

import constants.OrderStatus;
import models.Order;
import models.User;
import service.OrderService;
import service.UserService;
import servlets.CommonServletMethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/save")
public class SaveOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService service = new OrderService();
        UserService userService = new UserService();
        User user = CommonServletMethods.getUserFromCookies(req.getCookies());

        int id = Integer.parseInt(req.getParameter("id"));
        double price = 0;
        if (req.getParameter("price") != null) {
            price = Double.parseDouble(req.getParameter("price"));
        }
        Order order = service.getOrder(id);
        OrderStatus status = req.getParameter("status") == null ? order.getStatus() : OrderStatus.valueOf(req.getParameter("status"));
        User craftsman = null;
        if (req.getParameter("craftsmanId") != null) {
            craftsman = userService.getUser(Integer.parseInt(req.getParameter("craftsmanId")));
        }


        order.setStatus(status);
        order.setPrice(price);
        order.setCraftsman(craftsman);

        service.updateOrder(order);

        CommonServletMethods.forwardToOrders(req, resp, service, user);
    }
}
