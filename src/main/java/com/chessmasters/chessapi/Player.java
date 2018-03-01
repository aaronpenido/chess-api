package com.chessmasters.chessapi;

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

    public Player() {
        this.id = null;
        this.name = null;
    }

    public Player(PlayerRequest playerRequest) {
        this.id = null;
        this.name = playerRequest.getName();
    }

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
