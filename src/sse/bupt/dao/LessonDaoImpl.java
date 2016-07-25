package sse.bupt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sse.bupt.domain.Lesson;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
@Repository
public class LessonDaoImpl implements LessonDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(Lesson lesson) {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().saveOrUpdate(lesson);
    }

    @Override
    public void delete(Lesson lesson) {
        sessionFactory.getCurrentSession().delete(lesson);
    }

    @Override
    public List<Lesson> getLessonList(int lessonID) {
        return (List<Lesson>) sessionFactory.getCurrentSession().createQuery("from Lesson where id = :number").setParameter("number",lessonID).list();
    }

    @Override
    public Lesson getLesson(int lessonID) {
        List<Lesson> lessonList = this.getLessonList(lessonID);
        Lesson lesson = new Lesson();
        lesson.setId(lessonList.get(0).getId());
        lesson.setName(lessonList.get(0).getName());
        lesson.setOccurday(lessonList.get(0).getOccurday());
        lesson.setRoom(lessonList.get(0).getRoom());
        lesson.setOccurorder(lessonList.get(0).getOccurorder());
        lesson.setTeachername(lessonList.get(0).getTeachername());
        return lesson;
    }

    @Override
    public List<Lesson> getAllLesson() {
        return (List<Lesson>) sessionFactory.getCurrentSession().createQuery("from Lesson").list();
    }
}
