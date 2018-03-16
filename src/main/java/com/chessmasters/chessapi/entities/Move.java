package com.chessmasters.chessapi.entities;

import javax.persistence.*;

@Entity
public class Move {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    public Move(Game game) {
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }
}
