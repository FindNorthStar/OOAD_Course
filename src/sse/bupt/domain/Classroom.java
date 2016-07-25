package sse.bupt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Java on 2016/5/28.
 */
@Entity
public class Classroom {

    private int id;
    private String name;
    private int capacity;

    public Classroom(){

    }

    public Classroom(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
