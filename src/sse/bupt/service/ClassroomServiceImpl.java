package sse.bupt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sse.bupt.dao.ClassroomDao;
import sse.bupt.dao.LessonDao;
import sse.bupt.domain.Classroom;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Service
public class ClassroomServiceImpl implements ClassroomService{

    @Autowired
    private ClassroomDao classroomDao;

    @Override
    @Transactional
    public void insert(Classroom classroom) {
        classroomDao.insert(classroom);
    }

    @Override
    @Transactional
    public void delete(Classroom classroom) {
        classroomDao.delete(classroom);
    }

    @Override
    @Transactional
    public List<Classroom> getClassroomList(int classroomID) {
        return classroomDao.getClassroomList(classroomID);
    }

    @Override
    @Transactional
    public Classroom getClassroom(int classroomID) {
        return classroomDao.getClassroom(classroomID);
    }

    @Override
    @Transactional
    public List<Classroom> getAllClassroom() {
        return classroomDao.getAllClassroom();
    }
}
