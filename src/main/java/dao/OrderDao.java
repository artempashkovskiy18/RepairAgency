package dao;

import constants.DBColumnsNames;
import constants.OrderStatus;
import models.Order;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static OrderDao instance;

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

    public List<Order> getAllOrders() {
        List<Order> result = new ArrayList<>();

        String selectOrders = "select * from orders";

        Connection connection = ConnectionPool.getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet ordersResultSet = statement.executeQuery(selectOrders)) {

            while (ordersResultSet.next()) {
                int id = ordersResultSet.getInt(DBColumnsNames.ORDER_ID);
                double price = ordersResultSet.getDouble(DBColumnsNames.ORDER_PRICE);
                User user = UserDao.getInstance().getUserById(ordersResultSet.getInt(DBColumnsNames.ORDER_USER_ID));
                String car = ordersResultSet.getString(DBColumnsNames.ORDER_DESCRIPTION);
                OrderStatus status = getStatusById(ordersResultSet.getInt(DBColumnsNames.ORDER_STATUS_ID));

                result.add(new Order(id, price, car, user, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public Order getOrderById(int id) {
        Order result;

        Connection connection = ConnectionPool.getConnection();

        String selectOrders = "select * from orders where id_order = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectOrders)) {

            statement.setInt(1, id);
            ResultSet ordersResultSet = statement.executeQuery();

            ordersResultSet.next();
            double price = ordersResultSet.getDouble(DBColumnsNames.ORDER_PRICE);
            User user = UserDao.getInstance().getUserById(ordersResultSet.getInt(DBColumnsNames.ORDER_USER_ID));
            String car = ordersResultSet.getString(DBColumnsNames.ORDER_DESCRIPTION);
            OrderStatus status = getStatusById(ordersResultSet.getInt(DBColumnsNames.ORDER_STATUS_ID));


            result = new Order(id, price, car, user, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public boolean addOrder(Order order) {
        Connection connection = ConnectionPool.getConnection();

        String query = "insert into orders(price, id_user, description, id_status) values(?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
            insertStatement.setDouble(1, order.getPrice());
            insertStatement.setInt(2, order.getUser().getId());
            insertStatement.setString(3, order.getDescription());
            insertStatement.setInt(4, order.getStatus().getId());

            return insertStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean updateOrder(Order order) {
        Connection connection = ConnectionPool.getConnection();

        String query = "update orders set price = ?, id_user = ?, description = ?, id_status = ? where id_order = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
            updateStatement.setDouble(1, order.getPrice());
            updateStatement.setInt(2, order.getUser().getId());
            updateStatement.setString(3, order.getDescription());
            updateStatement.setInt(4, order.getStatus().getId());
            updateStatement.setInt(5, order.getId());

            return updateStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private OrderStatus getStatusById(int id){
        OrderStatus result = null;
        if(id == OrderStatus.PENDING_PAYMENT.getId()){
            result = OrderStatus.PENDING_PAYMENT;
        }else if (id == OrderStatus.PAID.getId()){
            result = OrderStatus.PAID;
        }else if (id == OrderStatus.COMPLETE.getId()){
            result = OrderStatus.COMPLETE;
        }else if (id == OrderStatus.CANCELED.getId()){
            result = OrderStatus.CANCELED;
        }
        return result;
    }
}
