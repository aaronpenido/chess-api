package com.chessmasters.chessapi;

import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.request.PlayerRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private final Long id;
    private final String name;
    private Color color;

    public Player() {
        this.id = null;
        this.name = null;
        this.color = null;
    }

    public Player(Color color) {
        this.id = null;
        this.name = null;
        this.color = color;
    }

    public Player(PlayerRequest playerRequest) {
        this.id = null;
        this.name = playerRequest.getName();
        this.color = null;
    }

    public Player(Color color, Long id, String name) {
        this.id = id;
        this.name = name;
        this.color = color;
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
