package sse.bupt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sse.bupt.domain.User;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(User user) {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(int userID) {
        sessionFactory.getCurrentSession().delete(this.getUser(userID));
    }

    @Override
    public void delete(User user){
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<User> getUserList(int userID) {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("from User where id = :number").setParameter("number",userID).list();
    }

    @Override
    public User getUser(int userID) {
        List<User> userList = this.getUserList(userID);
        User user = new User();
        user.setId(userList.get(0).getId());
        user.setUsername(userList.get(0).getUsername());
        user.setPassword(userList.get(0).getPassword());
        user.setIdentity(userList.get(0).getIdentity());
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("from User").list();
    }
}
