package servlets.servlet;

import constants.OrderStatus;
import models.Order;
import service.OrderService;
import servlets.CommonServletMethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//TODO do filtering statuses for different roles
@WebServlet("/save")
public class SaveOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService service = new OrderService();

        double price = Double.parseDouble(req.getParameter("price"));
        OrderStatus status = OrderStatus.valueOf(req.getParameter("status"));
        int id = Integer.parseInt(req.getParameter("id"));

        Order order = service.getOrder(id);
        order.setStatus(status);
        order.setPrice(price);

        service.updateOrder(order);

        CommonServletMethods.forwardToOrders(req, resp, service);
    }
}
