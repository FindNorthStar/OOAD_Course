package sse.bupt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Java on 2016/5/28.
 */
@Entity
public class Student implements Serializable {

    private int id;
    private String classes;
    private String lesson;

    public Student(){

    }

    public Student(int id, String classes, String lesson) {
        this.id = id;
        this.classes = classes;
        this.lesson = lesson;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
