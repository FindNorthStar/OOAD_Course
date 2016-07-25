package sse.bupt.dao;

import sse.bupt.domain.Lesson;
import sse.bupt.domain.Student;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
public interface StudentDao {

    void insert(Student student);

    void delete(int studentID);

    void delete(Student student);

    void update(Student student);

    public List<Student> getStudentList(int studentID);

    public Student getStudent(int studentID);

    public List<Integer> getLessonList(int studentID);

}
