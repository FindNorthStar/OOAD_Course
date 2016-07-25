package sse.bupt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sse.bupt.dao.LessonDao;
import sse.bupt.domain.Lesson;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Service
public class LessonServiceImpl implements LessonService{

    @Autowired
    private LessonDao lessonDao;

    @Override
    @Transactional
    public void insert(Lesson lesson) {
        lessonDao.insert(lesson);
    }

    @Override
    @Transactional
    public void delete(Lesson lesson) {
        lessonDao.delete(lesson);
    }

    @Override
    @Transactional
    public List<Lesson> getLessonList(int lessonID) {
        return lessonDao.getLessonList(lessonID);
    }

    @Override
    @Transactional
    public Lesson getLesson(int lessonID) {
        return lessonDao.getLesson(lessonID);
    }

    @Override
    @Transactional
    public List<Lesson> getAllLesson() {
        return lessonDao.getAllLesson();
    }
}
