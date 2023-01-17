package models;

import constants.OrderStatus;

public interface IOrder {
    void changeStatus(OrderStatus newStatus);
}
