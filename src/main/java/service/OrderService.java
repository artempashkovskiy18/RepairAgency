package service;

import dao.OrderDao;
import models.Order;
import models.User;

import java.util.List;

public class OrderService {
    private OrderDao orderDao = OrderDao.getInstance();


    public List<Order> getAllOrders(){
        return orderDao.getAllOrders();
    }

    public Order getOrder(int id){
        return orderDao.getOrderById(id);
    }

    public boolean addOrder(Order order){
        return orderDao.addOrder(order);
    }

    public boolean updateOrder(Order order){
        return orderDao.updateOrder(order);
    }

    public List<Order> getOrdersByCraftsman(User craftsman){
        return orderDao.getOrdersByCraftsman(craftsman);
    }
}
