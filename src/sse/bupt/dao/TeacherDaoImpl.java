package sse.bupt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sse.bupt.domain.Teacher;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Repository
public class TeacherDaoImpl implements TeacherDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(Teacher teacher) {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().saveOrUpdate(teacher);
    }

    @Override
    public void delete(int teacherID) {
        sessionFactory.getCurrentSession().delete(this.getTeacher(teacherID));
    }

    @Override
    public void delete(Teacher teacher){
        sessionFactory.getCurrentSession().delete(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        sessionFactory.getCurrentSession().saveOrUpdate(teacher);
    }

    @Override
    public List<Teacher> getTeacherList(int teacherID) {
        return (List<Teacher>) sessionFactory.getCurrentSession().createQuery("from Teacher where id = :number").setParameter("number",teacherID).list();
    }

    @Override
    public Teacher getTeacher(int teacherID) {
        List<Teacher> teacherList = this.getTeacherList(teacherID);
        Teacher teacher = new Teacher();
        teacher.setId(teacherList.get(0).getId());
        teacher.setLesson(teacherList.get(0).getLesson());
        return teacher;
    }

    @Override
    public int getLesson(int teacherID) {
        return Integer.parseInt(this.getTeacher(teacherID).getLesson());
    }
}
