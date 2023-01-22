package service;

import dao.OrderDao;
import models.implementations.Order;

import java.util.List;

public class OrderService {
    private static OrderService instance;

    private OrderService() {
    }

    public static OrderService getInstance (){
        if(instance ==  null){
            instance = new OrderService();
        }
        return instance;
    }


    public List<Order> getAllOrders(){
        OrderDao orderDao = OrderDao.getInstance();
        return orderDao.getAllOrders();
    }

    public Order getOrder(int id){
        OrderDao orderDao = OrderDao.getInstance();
        return orderDao.getOrderById(id);
    }

    public boolean removeOrder(Order order){
        OrderDao orderDao = OrderDao.getInstance();
        return orderDao.removeOrder(order);
    }

    public boolean addOrder(Order order){
        OrderDao orderDao = OrderDao.getInstance();
        return orderDao.addOrder(order);
    }

    public boolean updateOrder(Order order){
        OrderDao orderDao = OrderDao.getInstance();
        return orderDao.updateOrder(order);
    }
}
