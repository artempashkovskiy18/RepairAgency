package models.implementations;

import constants.OrderStatus;
import models.IOrder;

public class Order implements IOrder {
    private OrderStatus status;
    private int price;
    private final User user;
    private final String car;

    public Order(int price, String car, User user, OrderStatus status) {
        this.status = status;
        this.price = price;
        this.user = user;
        this.car = car;
    }

    @Override
    public void changeStatus(OrderStatus newStatus) {
        status = newStatus;
    }
}
