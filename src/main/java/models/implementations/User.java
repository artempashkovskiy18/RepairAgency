package models.implementations;

import constants.OrderStatus;
import models.IUser;

public class User implements IUser {
    private String name;
    private String phone;
    private String email;
    private String accountName;

    public User(String name, String phone, String email, String accountName) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accountName = accountName;
    }

    @Override
    public Order createOrder(int price, String car, OrderStatus status) {
        return new Order( price, car, this, status);
    }
}
