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
    private int moveOrder;

    public Move() {
    }

    public Move(Game game, int moveOrder) {
        this.game = game;
        this.moveOrder = moveOrder;
    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public int getMoveOrder() {
        return moveOrder;
    }
}
