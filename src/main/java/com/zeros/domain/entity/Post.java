package com.zeros.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "POST")
public class Post {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "BODY")
    private String body;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    private Post(){

    }
    public Post(String username, String body) {
        this.username = username;
        this.body = body;
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
