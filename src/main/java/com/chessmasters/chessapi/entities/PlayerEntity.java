package com.chessmasters.chessapi.entities;

import com.chessmasters.chessapi.enums.Color;

import javax.persistence.*;

@Entity(name = "Player")
public class PlayerEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Color color;

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
