package sse.bupt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Java on 2016/5/28.
 */
@Entity
public class Lesson {

    private int id;
    private String name;
    private int occurday;
    private String room;
    private int occurorder;
    private String teachername;

    public Lesson(){

    }

    public Lesson(int id, String name, int occurday, String room, int occurorder, String teachername) {
        this.id = id;
        this.name = name;
        this.occurday = occurday;
        this.room = room;
        this.occurorder = occurorder;
        this.teachername = teachername;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getOccurday() {
        return occurday;
    }

    public void setOccurday(int occurday) {
        this.occurday = occurday;
    }

    public int getOccurorder() {
        return occurorder;
    }

    public void setOccurorder(int occurorder) {
        this.occurorder = occurorder;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }
}
