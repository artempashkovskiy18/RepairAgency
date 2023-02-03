package models;

import constants.OrderStatus;

import java.sql.Time;
import java.sql.Date;
import java.util.Objects;


public class Order{
    private final int id;
    private OrderStatus status;
    private double price;
    private final Date date;
    private final Time time;
    private final User user;
    private final String description;
    private User craftsman = null;

    public Order(double price, String description, User user, OrderStatus status, Date date, Time time) {
        this.status = status;
        this.price = price;
        this.user = user;
        this.description = description;
        this.id = -1;
        this.date = date;
        this.time = time;
    }

    public Order(int id, double price, String description, User user, OrderStatus status, Date date, Time time, User craftsman) {
        this.status = status;
        this.price = price;
        this.user = user;
        this.description = description;
        this.id = id;
        this.date = date;
        this.time = time;
        this.craftsman = craftsman;
    }


    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCraftsman(User craftsman){
        this.craftsman = craftsman;
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

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public User getCraftsman() {
        return craftsman;
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
