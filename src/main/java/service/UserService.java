package service;

import dao.UserDao;
import models.User;

import java.util.List;

public class UserService {
    public UserService() {
    }

    public List<User> getAllUsers(){
        UserDao userDao = UserDao.getInstance();
        return userDao.getAllUsers();
    }

    public User getUser(int id){
        UserDao userDao = UserDao.getInstance();
        return userDao.getUserById(id);
    }

    public User getUserByEmail(String email){
        UserDao userDao = UserDao.getInstance();
        List<User> allUsers = userDao.getAllUsers();
        for (User user : allUsers) {
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public boolean checkIfUserExists(String email){
        return getUserByEmail(email) != null;
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