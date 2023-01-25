package dao;


import constants.DBColumnsNames;
import constants.Role;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    private UserDao() {
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();


        String selectUsers = "select * from users";
        Connection connection = ConnectionPool.getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet usersResultSet = statement.executeQuery(selectUsers)) {

            while (usersResultSet.next()) {
                int id = usersResultSet.getInt(DBColumnsNames.USER_ID);
                String name = usersResultSet.getString(DBColumnsNames.USER_NAME);
                String email = usersResultSet.getString(DBColumnsNames.USER_EMAIL);
                String phone = usersResultSet.getString(DBColumnsNames.USER_PHONE);
                String password = usersResultSet.getString(DBColumnsNames.USER_PASSWORD);
                Role role = getRoleById(usersResultSet.getInt(DBColumnsNames.USER_ROLE_ID));

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
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public User getUserById(int id) {
        String query = "select * from users where id_user = ?";
        Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString(DBColumnsNames.USER_NAME);
                String email = resultSet.getString(DBColumnsNames.USER_EMAIL);
                String phone = resultSet.getString(DBColumnsNames.USER_PHONE);
                String password = resultSet.getString(DBColumnsNames.USER_PASSWORD);
                Role role = getRoleById(resultSet.getInt(DBColumnsNames.USER_ROLE_ID));

                return new User(id, name, phone, email, password, role);
            } else {
                return null;
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
    }

    public User getUserByEmail(String email) {
        String query = "select * from users where email = ?";
        Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int id = resultSet.getInt(DBColumnsNames.USER_ID);
                String name = resultSet.getString(DBColumnsNames.USER_NAME);
                String phone = resultSet.getString(DBColumnsNames.USER_PHONE);
                String password = resultSet.getString(DBColumnsNames.USER_PASSWORD);
                Role role = getRoleById(resultSet.getInt(DBColumnsNames.USER_ROLE_ID));

                return new User(id, name, phone, email, password, role);
            } else {
                return null;
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
    }


    public boolean removeUser(User user) {
        Connection connection = ConnectionPool.getConnection();

        String query = "delete from users where " + DBColumnsNames.USER_ID + " = ?";

        try (PreparedStatement removeStatement = connection.prepareStatement(query)) {
            removeStatement.setInt(1, user.getId());
            return removeStatement.executeUpdate() != 0;
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

    public boolean addUser(User user) {
        Connection connection = ConnectionPool.getConnection();

        String query = "insert into users(" + DBColumnsNames.USER_ID + ", "
                + DBColumnsNames.USER_NAME + ", "
                + DBColumnsNames.USER_EMAIL + ", "
                + DBColumnsNames.USER_PASSWORD + ", "
                + DBColumnsNames.USER_ROLE_ID + ", "
                + DBColumnsNames.USER_PHONE + ") values(default, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
            insertStatement.setString(1, user.getName());
            insertStatement.setString(2, user.getEmail());
            insertStatement.setString(3, user.getPassword());
            insertStatement.setInt(4, user.getRole().getId());
            insertStatement.setString(5, user.getPhone());

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

    public boolean updateUser(User user) {
        Connection connection = ConnectionPool.getConnection();

        String query = "update users " +
                "set " + DBColumnsNames.USER_NAME + " = ?, "
                + DBColumnsNames.USER_EMAIL + " = ?, "
                + DBColumnsNames.USER_PASSWORD + " = ?, "
                + DBColumnsNames.USER_ROLE_ID + " = ?, "
                + DBColumnsNames.USER_PHONE + " = ? " +
                "where " + DBColumnsNames.USER_ID + " = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
            updateStatement.setString(1, user.getName());
            updateStatement.setString(2, user.getEmail());
            updateStatement.setString(3, user.getPassword());
            updateStatement.setInt(4, user.getRole().getId());
            updateStatement.setString(5, user.getPhone());
            updateStatement.setInt(6, user.getId());

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

    private Role getRoleById(int id) {
        Role result = null;
        if (id == Role.USER.getId()) {
            result = Role.USER;
        } else if (id == Role.MANAGER.getId()) {
            result = Role.MANAGER;
        } else if (id == Role.CRAFTSMAN.getId()) {
            result = Role.CRAFTSMAN;
        }
        return result;
    }


}
