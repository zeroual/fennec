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

    @Column(name = "BODY")
    private String body;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "POSTER_ID")
    private Member poster;

    private Post(){

    }
    public Post(String  body) {
        this.body = body;
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }



    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
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
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }

    public Member getPoster() {
        return poster;
    }

    public void setPoster(Member poster) {
        this.poster = poster;
    }
}
