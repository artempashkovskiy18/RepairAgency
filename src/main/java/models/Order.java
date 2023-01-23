package models;

import constants.OrderStatus;

import java.util.Objects;

public class Order{
    private int id;
    private OrderStatus status;
    private double price;


    private final User user;
    private final String description;

    public Order(double price, String description, User user, OrderStatus status) {
        this.status = status;
        this.price = price;
        this.user = user;
        this.description = description;
        this.id = -1;
    }

    public Order(int id, double price, String description, User user, OrderStatus status) {
        this.status = status;
        this.price = price;
        this.user = user;
        this.description = description;
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.price, price) == 0 && status == order.status && user.equals(order.user) && description.equals(order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, price, user, description);
    }
}
