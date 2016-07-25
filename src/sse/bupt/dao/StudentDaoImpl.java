package sse.bupt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sse.bupt.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Repository
public class StudentDaoImpl implements StudentDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(Student student) {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().saveOrUpdate(student);
    }

    @Override
    public void delete(int studentID) {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().delete(this.getStudent(studentID));
    }

    @Override
    public void delete(Student student){
        sessionFactory.getCurrentSession().delete(student);
    }

    @Override
    public void update(Student student) {
        sessionFactory.getCurrentSession().saveOrUpdate(student);
    }

    @Override
    public List<Student> getStudentList(int studentID) {
        return (List<Student>) sessionFactory.getCurrentSession().createQuery("from Student where id = :number").setParameter("number",studentID).list();
    }

    @Override
    public Student getStudent(int studentID) {
        List<Student> studentList = this.getStudentList(studentID);
        Student student = new Student();
        student.setId(studentList.get(0).getId());
        student.setClasses(studentList.get(0).getClasses());
        student.setLesson(studentList.get(0).getLesson());
        return student;
    }

    @Override
    public List<Integer> getLessonList(int studentID) {
        Student student = this.getStudent(studentID);
        List<Integer> lessonList = new ArrayList<>();
        String[] abc = student.getLesson().split(";");
        if (abc[0].equals("")){
            return null;
        }
        for (String s : abc){
            lessonList.add(Integer.parseInt(s));
        }
        return lessonList;
    }
}
