package sse.bupt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Java on 2016/5/28.
 */
@Entity
public class User implements Serializable {

    private int id;
    private String username;
    private String password;
    private String identity;

    public User() {
    }

    public User(int id, String username, String password, String identity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
