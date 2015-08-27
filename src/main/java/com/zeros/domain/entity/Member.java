package com.zeros.domain.entity;

import org.springframework.social.connect.UsersConnectionRepository;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    public Member() {
    }

    public Member(String userId) {
        this.id=userId;
    }

    public Member(String userId, String displayName) {
        this(userId);
        this.displayName=displayName;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
