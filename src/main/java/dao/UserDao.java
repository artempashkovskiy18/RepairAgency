package dao;


import constants.DBColumnsNames;
import constants.Role;
import models.implementations.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        //Connection connection = ConnectionPool.getInstance().getConnection();
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
        }finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return result;
    }
}
