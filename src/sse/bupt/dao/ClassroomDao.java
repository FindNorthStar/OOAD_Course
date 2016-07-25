package sse.bupt.dao;

import sse.bupt.domain.Classroom;

import java.util.List;

/**
 * Created by Java on 2016/5/28.
 */
public interface ClassroomDao {

    void insert(Classroom classroom);

    void delete(Classroom classroom);

    public List<Classroom> getClassroomList(int classroomID);

    public Classroom getClassroom(int classroomID);

    public List<Classroom> getAllClassroom();
}
