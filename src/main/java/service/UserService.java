package service;

import dao.UserDao;
import models.implementations.User;

import java.util.List;

public class UserService {
    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance (){
        if(instance ==  null){
            instance = new UserService();
        }
        return instance;
    }


    public List<User> getAllUsers(){
        UserDao userDao = UserDao.getInstance();
        return userDao.getAllUsers();
    }

    public User getUser(int id){
        UserDao userDao = UserDao.getInstance();
        return userDao.getUserById(id);
    }

    public boolean removeUser(User user){
        UserDao userDao = UserDao.getInstance();
        return userDao.removeUser(user);
    }

    public boolean addUser(User user){
        UserDao userDao = UserDao.getInstance();
        return userDao.addUser(user);
    }

    public boolean updateUser(User user){
        UserDao userDao = UserDao.getInstance();
        return userDao.updateUser(user);
    }
}
