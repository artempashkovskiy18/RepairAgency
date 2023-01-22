package dao;

import constants.OrderStatus;
import models.implementations.Order;
import models.implementations.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    @Test
    void getAllOrders() {
        List<Order> ordersExpected = new ArrayList<>();
        ordersExpected.add(new Order(1, 1300, "vaz2109", UserDao.getInstance().getUserById(1), OrderStatus.PENDING_PAYMENT));
        List<Order> ordersActual = OrderDao.getInstance().getAllOrders();
        assertEquals(ordersExpected, ordersActual);
    }

    @Test
    void removeOrder() {
        Order order = new Order(1, 1300, "vaz2109", UserDao.getInstance().getUserById(1), OrderStatus.PENDING_PAYMENT);
        assertTrue(OrderDao.getInstance().removeOrder(order));
    }

    @Test
    void addOrder() {
        Order order = new Order(1300, "vaz2109", UserDao.getInstance().getUserById(1), OrderStatus.PENDING_PAYMENT);
        assertTrue(OrderDao.getInstance().addOrder(order));
    }

    @Test
    void updateOrder() {
        Order order = new Order(1, 1300, "vaz2110", UserDao.getInstance().getUserById(1), OrderStatus.PENDING_PAYMENT);
        assertTrue(OrderDao.getInstance().updateOrder(order));
    }
}