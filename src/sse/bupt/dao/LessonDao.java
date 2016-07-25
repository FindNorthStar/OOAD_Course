package sse.bupt.dao;

import sse.bupt.domain.Lesson;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
public interface LessonDao {

    void insert(Lesson lesson);

    void delete(Lesson lesson);

    public List<Lesson> getLessonList(int lessonID);

    public Lesson getLesson(int lessonID);

    public List<Lesson> getAllLesson();

}
