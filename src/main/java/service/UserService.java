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

    public boolean checkIfUserExists(User user){
        boolean b = getUserByEmail(user.getEmail()) != null;
        return b;
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

    //TODO wrong regex
    public boolean checkIfUserValid(User user){
        boolean em = user.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@" +
                "[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");
        boolean ph = user.getPhone().matches("^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{3}\\\\))|\\\\d{3})[- .]?\\\\d{3}[- .]?\\\\d{4}$");
        return em && ph;
    }
}
