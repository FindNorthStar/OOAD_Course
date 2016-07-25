package sse.bupt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Java on 2016/5/28.
 */
@Entity
public class Teacher {

    private int id;
    private String lesson;

    public Teacher(){

    }

    public Teacher(int id, String lesson) {
        this.id = id;
        this.lesson = lesson;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
