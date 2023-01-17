package models;

import constants.OrderStatus;
import models.implementations.Order;

public interface IUser {

    Order createOrder(int price, String car, OrderStatus status);
}
