package servlets.servlet;

import constants.OtherConstants;
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
import java.util.Comparator;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService service = new OrderService();
        User user = CommonServletMethods.getUserFromCookies(req.getCookies());

        String sortBy = req.getParameter("sort");
        Comparator<Order> comparator = getComparator(sortBy);
        CommonServletMethods.forwardToOrders(req, resp, service, user, comparator);
    }

    private Comparator<Order> getComparator(String sortBy) {
        switch (sortBy) {
            case OtherConstants.SORT_OPTION_DATE:
                return Comparator.comparing(Order::getDate);
            case OtherConstants.SORT_OPTION_COST:
                return (o1, o2) -> (int) Math.round(o1.getPrice() - o2.getPrice());
            case OtherConstants.SORT_OPTION_STATUS:
                return Comparator.comparing(Order::getStatus);
            default:
                return (o1, o2) -> {
                    if (o1.getCraftsman() == null) {
                        return -1;
                    } else if (o2.getCraftsman() == null) {
                        return 1;
                    } else {
                        return o1.getCraftsman().getName().compareTo(o2.getCraftsman().getName());
                    }
                };
        }
    }
}
