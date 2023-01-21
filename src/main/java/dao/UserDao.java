package dao;


import constants.DBColumnsNames;
import constants.Role;
import models.implementations.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private static UserDao instance;
    private static final String USER_NAME = "user_name";
    private static final String USER_ROLE = "role_name";

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();


        String selectUsers = "select us.id_user, us.name user_name, us.phone, us.email, us.password, rls.name role_name " +
                "from users us " +
                "inner join roles rls " +
                "on us.id_role = rls.id_role";
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet usersResultSet = statement.executeQuery(selectUsers)) {

            while (usersResultSet.next()) {
                int id = usersResultSet.getInt(DBColumnsNames.USER_ID);
                String name = usersResultSet.getString(UserDao.USER_NAME);
                String email = usersResultSet.getString(DBColumnsNames.USER_EMAIL);
                String phone = usersResultSet.getString(DBColumnsNames.USER_PHONE);
                String password = usersResultSet.getString(DBColumnsNames.USER_PASSWORD);
                Role role = getRoleByRoleString(usersResultSet.getString(UserDao.USER_ROLE));

                result.add(new User(id,
                        name,
                        phone,
                        email,
                        password,
                        role));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return result;
    }

    public User getUserById(int id){
        List<User> allUsers = getAllUsers();
        for (User user : allUsers) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public boolean removeUser(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "delete from users where " + DBColumnsNames.USER_ID + " = ?";

        try (PreparedStatement removeStatement = connection.prepareStatement(query)) {
            removeStatement.setInt(1, user.getId());
            return removeStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public boolean addUser(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "insert into users("+ DBColumnsNames.USER_ID +", "
                + DBColumnsNames.USER_NAME +", "
                + DBColumnsNames.USER_EMAIL +", "
                + DBColumnsNames.USER_PASSWORD +", "
                + DBColumnsNames.USER_ROLE_ID +", "
                + DBColumnsNames.USER_PHONE +") values(default, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
            insertStatement.setString(1, user.getName());
            insertStatement.setString(2, user.getEmail());
            insertStatement.setString(3, user.getPassword());
            insertStatement.setInt(4, getRoleIdByRole(user.getRole()));
            insertStatement.setString(5, user.getPhone());

            return insertStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public boolean updateUser(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "update users " +
                "set "+ DBColumnsNames.USER_NAME +" = ?, "
                + DBColumnsNames.USER_EMAIL +" = ?, "
                + DBColumnsNames.USER_PASSWORD +" = ?, "
                + DBColumnsNames.USER_ROLE_ID +" = ?, "
                + DBColumnsNames.USER_PHONE +" = ? " +
                "where "+ DBColumnsNames.USER_ID +" = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
            updateStatement.setString(1, user.getName());
            updateStatement.setString(2, user.getEmail());
            updateStatement.setString(3, user.getPassword());
            updateStatement.setInt(4, getRoleIdByRole(user.getRole()));
            updateStatement.setString(5, user.getPhone());
            updateStatement.setInt(6, user.getId());

            return updateStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    //TODO
    private int getRoleIdByRole(Role role){
        int roleId = 0;
        switch (role) {
            case USER:
                roleId = 1;
                break;
            case MANAGER:
                roleId = 2;
                break;
            case CRAFTSMAN:
                roleId = 3;
                break;
        }
        return roleId;
    }

    //TODO
    private Role getRoleByRoleString(String stringRole){
        Role role = null;
        switch (stringRole) {
            case "user":
                role = Role.USER;
                break;
            case "manager":
                role = Role.MANAGER;
                break;
            case "craftsman":
                role = Role.CRAFTSMAN;
                break;
        }
        return role;
    }


}
