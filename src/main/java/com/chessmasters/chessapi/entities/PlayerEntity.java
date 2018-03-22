package com.chessmasters.chessapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Player")
public class PlayerEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public PlayerEntity() {
    }

    public PlayerEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
