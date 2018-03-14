package com.chessmasters.chessapi.entities;

import com.chessmasters.chessapi.enums.GameStatus;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    public Game() {
    }

    public Game(GameStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }
}
