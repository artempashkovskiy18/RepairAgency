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

        double price = 0;
        if (req.getParameter("price") != null) {
            price = Double.parseDouble(req.getParameter("price"));
        }
        OrderStatus status = OrderStatus.valueOf(req.getParameter("status"));
        int id = Integer.parseInt(req.getParameter("id"));
        User craftsman = null;
        if (req.getParameter("craftsmanId") != null) {
            craftsman = userService.getUser(Integer.parseInt(req.getParameter("craftsmanId")));
        }


        Order order = service.getOrder(id);
        order.setStatus(status);
        order.setPrice(price);
        order.setCraftsman(craftsman);

        service.updateOrder(order);

        CommonServletMethods.forwardToOrders(req, resp, service, user);
    }
}
