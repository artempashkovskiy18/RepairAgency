package dao;

import constants.Role;
import models.User;
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
                "123",
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
                "artempashkovskiy19@gmail.com",
                "123",
                Role.CRAFTSMAN);
        assertTrue(userDao.addUser(Artem));
    }

    @Test
    void updateUser() {
        User Artem = new User(2,"Artem",
                "+380964568447",
                "artempashkovskiy19@gmail.com",
                "123",
                Role.CRAFTSMAN);
        assertTrue(UserDao.getInstance().updateUser(Artem));

    }

    @Test
    void getUserById() {
        User Artem = new User(1,"Artem",
                "+380964568447",
                "artempashkovskiy18@gmail.com",
                "123",
                Role.MANAGER);
        assertEquals(Artem, UserDao.getInstance().getUserById(1));
    }

    @Test
    void getUserByEmail() {
        User wrongUser = UserDao.getInstance().getUserByEmail("ijfgirj");
        User rightUser = UserDao.getInstance().getUserByEmail("artempashkovskiy18@gmail.com");
        User Artem = new User(1,"Artem",
                "+380964568447",
                "artempashkovskiy18@gmail.com",
                "123",
                Role.MANAGER);
        assertNull(wrongUser);
        assertEquals(Artem, rightUser);
    }
}