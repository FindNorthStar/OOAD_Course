package sse.bupt.dao;

import sse.bupt.domain.Teacher;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
public interface TeacherDao {
    void insert(Teacher teacher);

    void delete(int teacherID);

    void delete(Teacher teacher);

    void update(Teacher teacher);

    public List<Teacher> getTeacherList(int teacherID);

    public Teacher getTeacher(int teacherID);

    public int getLesson(int teacherID);
}
