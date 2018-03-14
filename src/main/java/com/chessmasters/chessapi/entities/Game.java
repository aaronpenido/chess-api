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
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    public Game() {
    }

    public Game(Player player, GameStatus status) {
        this.player = player;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Player getPlayer() {
        return player;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
