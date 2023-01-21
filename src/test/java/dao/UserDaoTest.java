package dao;

import constants.Role;
import models.implementations.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void getAllUsers() {
        UserDao userDao = UserDao.getInstance();
        List<User> usersActual = userDao.getAllUsers();
        List<User> usersExpected = new ArrayList<>();
        usersExpected.add(new User(1,
                "Artem",
                "+380964568447",
                "artempashkovskiy18@gmail.com",
                "12345",
                Role.MANAGER));
        assertEquals(usersExpected, usersActual);
    }

    @Test
    void removeUser() {
        User Artem = new User(5,"Artem",
                "+380964568447",
                "artempashkovskiy18@gmail.com",
                "123",
                Role.MANAGER);
        assertTrue(UserDao.getInstance().removeUser(Artem));
    }

    @Test
    void addUser() {
        UserDao userDao = UserDao.getInstance();
        User Artem = new User("Artem",
                "+380964568447",
                "artempashkovskiy18@gmail.com",
                "123",
                Role.MANAGER);
        assertTrue(userDao.addUser(Artem));
    }

    @Test
    void updateUser() {
        User Artem = new User(1,"Artem",
                "+380964568447",
                "artempashkovskiy18@gmail.com",
                "12345",
                Role.MANAGER);
        assertTrue(UserDao.getInstance().updateUser(Artem));

    }
}