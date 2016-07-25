package sse.bupt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sse.bupt.domain.Classroom;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Repository
public class ClassroomDaoImpl implements ClassroomDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(Classroom classroom) {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().saveOrUpdate(classroom);
    }

    @Override
    public void delete(Classroom classroom) {
        sessionFactory.getCurrentSession().delete(classroom);
    }

    @Override
    public List<Classroom> getClassroomList(int classroomID) {
        return (List<Classroom>) sessionFactory.getCurrentSession().createQuery("from Classroom where id = :number").setParameter("number",classroomID).list();
    }

    @Override
    public Classroom getClassroom(int classroomID) {
        List<Classroom> classroomList = this.getClassroomList(classroomID);
        Classroom classroom = new Classroom();
        classroom.setId(classroomList.get(0).getId());
        classroom.setName(classroomList.get(0).getName());
        classroom.setCapacity(classroomList.get(0).getCapacity());
        return classroom;
    }

    @Override
    public List<Classroom> getAllClassroom() {
        return (List<Classroom>) sessionFactory.getCurrentSession().createQuery("from Classroom").list();
    }
}
