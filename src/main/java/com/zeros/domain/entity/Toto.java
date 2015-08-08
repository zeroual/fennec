package com.zeros.domain.entity;

import javax.persistence.*;

@Entity
public class Toto {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
