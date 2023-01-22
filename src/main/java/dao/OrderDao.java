package dao;

import constants.DBColumnsNames;
import constants.OrderStatus;
import models.implementations.Order;
import models.implementations.User;

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

        Connection connection = ConnectionPool.getInstance().getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet ordersResultSet = statement.executeQuery(selectOrders)) {

            while (ordersResultSet.next()) {
                int id = ordersResultSet.getInt(DBColumnsNames.ORDER_ID);
                double price = ordersResultSet.getDouble(DBColumnsNames.ORDER_PRICE);
                User user = UserDao.getInstance().getUserById(ordersResultSet.getInt(DBColumnsNames.ORDER_USER_ID));
                String car = ordersResultSet.getString(DBColumnsNames.ORDER_CAR);
                OrderStatus status = StatusDao.getInstance().getStatusById(ordersResultSet.getInt(DBColumnsNames.ORDER_STATUS_ID));

                result.add(new Order(id, price, car, user, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return result;
    }

    public Order getOrderById(int id){
        List<Order> allOrders = getAllOrders();
        for (Order order : allOrders) {
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    public boolean removeOrder(Order order) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "delete from orders where " + DBColumnsNames.ORDER_ID + " = ?";

        try (PreparedStatement removeStatement = connection.prepareStatement(query)) {
            removeStatement.setInt(1, order.getId());
            return removeStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public boolean addOrder(Order order) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "insert into orders(" + DBColumnsNames.ORDER_ID + ", "
                + DBColumnsNames.ORDER_PRICE + ", "
                + DBColumnsNames.ORDER_USER_ID + ", "
                + DBColumnsNames.ORDER_CAR + ", "
                + DBColumnsNames.ORDER_STATUS_ID + ") values(default, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
            insertStatement.setDouble(1, order.getPrice());
            insertStatement.setInt(2, order.getUser().getId());
            insertStatement.setString(3, order.getCar());
            insertStatement.setInt(4, StatusDao.getInstance().getStatusIdByStatus(order.getStatus()));

            return insertStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public boolean updateOrder(Order order) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "update orders " +
                "set " + DBColumnsNames.ORDER_PRICE + " = ?, "
                + DBColumnsNames.ORDER_USER_ID + " = ?, "
                + DBColumnsNames.ORDER_CAR + " = ?, "
                + DBColumnsNames.ORDER_STATUS_ID + " = ? "
                + "where " + DBColumnsNames.ORDER_ID + " = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
            updateStatement.setDouble(1, order.getPrice());
            updateStatement.setInt(2, order.getUser().getId());
            updateStatement.setString(3, order.getCar());
            updateStatement.setInt(4, StatusDao.getInstance().getStatusIdByStatus(order.getStatus()));
            updateStatement.setInt(5, order.getId());

            return updateStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
