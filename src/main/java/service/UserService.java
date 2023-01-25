package service;

import dao.UserDao;
import models.User;

import java.util.List;

public class UserService {
    UserDao userDao = UserDao.getInstance();

    public UserService() {
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public User getUser(int id){
        return userDao.getUserById(id);
    }

    public User getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    public boolean removeUser(User user){
        return userDao.removeUser(user);
    }

    public boolean addUser(User user){
        return userDao.addUser(user);
    }

    public boolean updateUser(User user){
        return userDao.updateUser(user);
    }

    public boolean checkIfUserValid(User user){
        boolean em = user.getEmail().matches("^[a-zA-Z0-9_!#$%&’*+=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        boolean ph1 = user.getPhone().matches("^(\\+38)?\\d{10}$");
        return em && ph1;
    }
}
