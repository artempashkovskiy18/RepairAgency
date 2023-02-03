package dao;

import constants.OrderStatus;
import models.Order;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    @Test
    void getAllOrders() {
        List<Order> ordersExpected = new ArrayList<>();
        ordersExpected.add(new Order(1,
                1300,
                "vaz2109",
                UserDao.getInstance().getUserById(1),
                OrderStatus.WAITING,
                new Date(2022, 12, 10),
                new Time(13,0,0),
                UserDao.getInstance().getUserById(2)));
        List<Order> ordersActual = OrderDao.getInstance().getAllOrders();
        assertEquals(ordersExpected, ordersActual);
    }

    @Test
    void addOrder() {
        Order order = new Order(1300,
                "vaz2109",
                UserDao.getInstance().getUserById(1),
                OrderStatus.WAITING,
                new Date(2022, 12, 10),
                new Time(13,0,0));
        assertTrue(OrderDao.getInstance().addOrder(order));
    }

    @Test
    void updateOrder() {
        Order order = new Order(3,
                1300,
                "vaz2110",
                UserDao.getInstance().getUserById(1),
                OrderStatus.PENDING_PAYMENT,
                new Date(2022, 12, 10),
                new Time(13,0,0),
                UserDao.getInstance().getUserById(2));
        assertTrue(OrderDao.getInstance().updateOrder(order));
    }

    @Test
    void getOrderById() {
        Order order = new Order(3,
                1300,
                "vaz2110",
                UserDao.getInstance().getUserById(1),
                OrderStatus.PENDING_PAYMENT,
                new Date(2022, 12, 10),
                new Time(13,0,0),
                UserDao.getInstance().getUserById(2));
        assertEquals(OrderDao.getInstance().getOrderById(3), order);
    }
}