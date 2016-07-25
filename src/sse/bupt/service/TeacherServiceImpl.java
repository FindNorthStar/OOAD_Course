package sse.bupt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sse.bupt.dao.TeacherDao;
import sse.bupt.domain.Teacher;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherDao teacherDao;

    @Override
    @Transactional
    public void insert(Teacher teacher) {
        teacherDao.insert(teacher);
    }

    @Override
    @Transactional
    public void delete(int teacherID) {
        teacherDao.delete(teacherID);
    }

    @Override
    @Transactional
    public void delete(Teacher teacher){
        teacherDao.delete(teacher);
    }

    @Override
    @Transactional
    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }

    @Override
    @Transactional
    public List<Teacher> getTeacherList(int teacherID) {
        return teacherDao.getTeacherList(teacherID);
    }

    @Override
    @Transactional
    public Teacher getTeacher(int teacherID) {
        return teacherDao.getTeacher(teacherID);
    }

    @Override
    @Transactional
    public int getLesson(int teacherID) {
        return teacherDao.getLesson(teacherID);
    }
}
