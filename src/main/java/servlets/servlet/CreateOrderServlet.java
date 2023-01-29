package servlets.servlet;

import constants.OrderStatus;
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
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService service = new OrderService();
        String description = req.getParameter("description");

        String timeStr = req.getParameter("time") + ":00";
        Time time = Time.valueOf(timeStr);
        String dateStr = req.getParameter("date");
        Date date = Date.valueOf(dateStr);

        Cookie[] cookies = req.getCookies();

        if(date.after(Date.valueOf(LocalDate.now().plusDays(7))) ||
                date.before(Date.valueOf(LocalDate.now()))){
            CommonServletMethods.forwardToErrorPage(req, resp, "you entered wrong date");
        }else if(time.before(Time.valueOf("09:00:00")) ||
                time.after(Time.valueOf("18:00:00"))){
            CommonServletMethods.forwardToErrorPage(req, resp, "you entered wrong time");
        }else if(cookies == null){
            CommonServletMethods.forwardToErrorPage(req, resp, "you are not logged in");
        }else{
            User user = CommonServletMethods.getUserFromCookies(cookies);
            if(user == null){
                CommonServletMethods.forwardToErrorPage(req, resp, "no such user. Try to log in");
            }else {
                Order order = new Order(0, description, user, OrderStatus.WAITING, date, time);
                service.addOrder(order);
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
    }
}
