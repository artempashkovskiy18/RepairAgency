package servlets;

import constants.OtherConstants;
import constants.Role;
import models.Order;
import models.User;
import service.OrderService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommonServletMethods {
    private static UserService service = new UserService();
    public static void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute(OtherConstants.ERROR_PAGE_MESSAGE_NAME, errorMessage);
        request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }

    public static User getUserFromCookies(Cookie[] cookies) {
        User result = null;
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("email")){
                result = service.getUserByEmail(cookie.getValue());
            }
        }
        return result;
    }

    public static void forwardToOrders(HttpServletRequest request, HttpServletResponse response, OrderService service, User user) throws ServletException, IOException {
        List<Order> orders;
        if(user.getRole() == Role.CRAFTSMAN){
            orders = service.getOrdersByCraftsman(user);
        }else{
            orders = service.getAllOrders();
        }
        request.setAttribute("orders", orders);
        request.getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
    }
}
