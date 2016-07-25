package sse.bupt.dao;

import sse.bupt.domain.User;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
public interface UserDao {
    void insert(User user);

    void delete(int userID);

    void delete(User user);

    void update(User user);

    public List<User> getUserList(int userID);

    public User getUser(int userID);

    public List<User> getAllUser();

}
