package sse.bupt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sse.bupt.dao.StudentDao;
import sse.bupt.domain.Student;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional
    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    @Transactional
    public void delete(int studentID) {
        studentDao.delete(studentID);
    }

    @Override
    @Transactional
    public void delete(Student student){
        studentDao.delete(student);
    }

    @Override
    @Transactional
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    @Transactional
    public List<Student> getStudentList(int studentID) {
        return studentDao.getStudentList(studentID);
    }

    @Override
    @Transactional
    public Student getStudent(int studentID) {
        return studentDao.getStudent(studentID);
    }

    @Override
    @Transactional
    public List<Integer> getLessonList(int studentID) {
        return studentDao.getLessonList(studentID);
    }
}
