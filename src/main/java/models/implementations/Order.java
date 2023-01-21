package models.implementations;

import constants.OrderStatus;
import models.IOrder;

import java.util.Objects;

public class Order implements IOrder {
    private int id;
    private OrderStatus status;
    private double price;


    private final User user;
    private final String car;

    public Order(double price, String car, User user, OrderStatus status) {
        this.status = status;
        this.price = price;
        this.user = user;
        this.car = car;
        this.id = -1;
    }

    public Order(int id, double price, String car, User user, OrderStatus status) {
        this.status = status;
        this.price = price;
        this.user = user;
        this.car = car;
        this.id = id;
    }

    @Override
    public void changeStatus(OrderStatus newStatus) {
        status = newStatus;
    }


    public int getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public String getCar() {
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.price, price) == 0 && status == order.status && user.equals(order.user) && car.equals(order.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, price, user, car);
    }
}
