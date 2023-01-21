package dao;

import constants.DBColumnsNames;
import constants.OrderStatus;
import constants.Role;
import models.implementations.Order;
import models.implementations.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RolesDao {
    private static RolesDao instance;

    public static RolesDao getInstance() {
        if (instance == null) {
            instance = new RolesDao();
        }
        return instance;
    }


    public Map<Integer, Role> getAllRoles() {
        Map<Integer, Role> result = new HashMap<>();

        String selectRoles = "select * from roles";

        Connection connection = ConnectionPool.getInstance().getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet rolesResultSet = statement.executeQuery(selectRoles)) {

            while (rolesResultSet.next()) {
                int id = rolesResultSet.getInt(DBColumnsNames.ROLE_ID);
                String name = rolesResultSet.getString(DBColumnsNames.ROLE_NAME);

                result.put(id, Role.valueOf(name.toUpperCase()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return result;
    }

    public Role getRoleById(int id){
        Map<Integer, Role> roles = getAllRoles();
        Role result = null;
        for (Map.Entry<Integer, Role> entry : roles.entrySet()) {
            if(entry.getKey() == id){
                result = entry.getValue();
            }
        }
        return result;
    }

    public int getRoleIdByRole(Role role){
        Map<Integer, Role> rolesWithIds = getAllRoles();
        int id = 0;
        for (Map.Entry<Integer, Role> entry : rolesWithIds.entrySet()) {
            if(entry.getValue() == role){
                id = entry.getKey();
            }
        }
        return id;
    }

    public boolean removeRole(Role role) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "delete from roles where " + DBColumnsNames.ROLE_NAME + " = ?";

        try (PreparedStatement removeStatement = connection.prepareStatement(query)) {
            removeStatement.setString(1, role.getName());
            return removeStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public boolean addRole(Role role) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "insert into roles(" + DBColumnsNames.ROLE_ID + ", "
                + DBColumnsNames.ROLE_NAME+ ") values(default, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
            insertStatement.setString(1, role.name().toLowerCase());

            return insertStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    //public boolean updateRole(Role role) {
    //    Connection connection = ConnectionPool.getInstance().getConnection();
//
    //    String query = "update roles " +
    //            "set " + DBColumnsNames.ROLE_NAME + " = ? "
    //            + "where " + DBColumnsNames.ROLE_ID + " = ?";
    //    try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
    //        updateStatement.setString(1, role.name().toLowerCase());
    //        updateStatement.setInt(2, getRoleIdByRole(role));
//
    //        return updateStatement.executeUpdate() != 0;
    //    } catch (SQLException e) {
    //        throw new RuntimeException(e);
    //    } finally {
    //        ConnectionPool.getInstance().releaseConnection(connection);
    //    }
    //}
}
