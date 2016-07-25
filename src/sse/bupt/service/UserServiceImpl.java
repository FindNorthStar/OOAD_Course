package sse.bupt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sse.bupt.dao.UserDao;
import sse.bupt.domain.User;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    @Transactional
    public void delete(int userID) {
        userDao.delete(userID);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public List<User> getUserList(int userID) {
        return userDao.getUserList(userID);
    }

    @Override
    @Transactional
    public User getUser(int userID) {
        return userDao.getUser(userID);
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
