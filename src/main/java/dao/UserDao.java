package dao;


import constants.DBColumnsNames;
import constants.Role;
import models.implementations.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
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


        String selectUsers = "select us.id_user, us.name user_name, us.phone, us.email, us.password, rls.name role_name \n" +
                "from users us \n" +
                "inner join roles rls \n" +
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
                String stringRole = usersResultSet.getString(UserDao.USER_ROLE);
                Role role = Role.USER;

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

    public boolean removeUser(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "delete from users where" + DBColumnsNames.USER_ID + " = " + user.getId();

        try (PreparedStatement removeStatement = connection.prepareStatement(query)) {
            return removeStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public boolean addUser(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "insert into users(?, ?, ?, ?, ?, ?) values(default, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
            insertStatement.setString(1, DBColumnsNames.USER_ID);
            insertStatement.setString(2, DBColumnsNames.USER_NAME);
            insertStatement.setString(3, DBColumnsNames.USER_EMAIL);
            insertStatement.setString(4, DBColumnsNames.USER_PASSWORD);
            insertStatement.setString(5, DBColumnsNames.USER_ROLE_ID);
            insertStatement.setString(6, DBColumnsNames.USER_PHONE);


            insertStatement.setString(7, user.getName());
            insertStatement.setString(8, user.getEmail());
            insertStatement.setString(9, user.getPassword());
            insertStatement.setString(11, user.getPhone());

            //TODO
            switch (user.getRole()) {
                case USER:
                    insertStatement.setInt(10, 1);
                    break;
                case MANAGER:
                    insertStatement.setInt(10, 2);
                    break;
                case CRAFTSMAN:
                    insertStatement.setInt(10, 3);
                    break;
            }

            return insertStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
