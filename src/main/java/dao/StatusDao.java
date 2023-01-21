package dao;

import constants.DBColumnsNames;
import constants.OrderStatus;
import constants.Role;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StatusDao {
    private static StatusDao instance;

    public static StatusDao getInstance() {
        if (instance == null) {
            instance = new StatusDao();
        }
        return instance;
    }


    public Map<Integer, OrderStatus> getAllStatuses() {
        Map<Integer, OrderStatus> result = new HashMap<>();

        String selectRoles = "select * from statuses";

        Connection connection = ConnectionPool.getInstance().getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet statusesResultSet = statement.executeQuery(selectRoles)) {

            while (statusesResultSet.next()) {
                int id = statusesResultSet.getInt(DBColumnsNames.STATUS_ID);
                String name = statusesResultSet.getString(DBColumnsNames.STATUS_NAME);

                result.put(id, OrderStatus.valueOf(name.toUpperCase()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return result;
    }

    public OrderStatus getStatusById(int id){
        Map<Integer, OrderStatus> roles = getAllStatuses();
        OrderStatus result = null;
        for (Map.Entry<Integer, OrderStatus> entry : roles.entrySet()) {
            if(entry.getKey() == id){
                result = entry.getValue();
            }
        }
        return result;
    }

    public int getStatusIdByStatus(OrderStatus status){
        Map<Integer, OrderStatus> statusesWithIds = getAllStatuses();
        int id = 0;
        for (Map.Entry<Integer, OrderStatus> entry : statusesWithIds.entrySet()) {
            if(entry.getValue() == status){
                id = entry.getKey();
            }
        }
        return id;
    }

    public boolean removeStatus(OrderStatus status) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "delete from statuses where " + DBColumnsNames.STATUS_NAME + " = ?";

        try (PreparedStatement removeStatement = connection.prepareStatement(query)) {
            removeStatement.setString(1, status.name().toLowerCase());
            return removeStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public boolean addStatus(OrderStatus status) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "insert into statuses(" + DBColumnsNames.STATUS_ID + ", "
                + DBColumnsNames.STATUS_NAME+ ") values(default, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
            insertStatement.setString(1, status.name().toLowerCase());

            return insertStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
