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
    @OneToOne
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private Player player2;

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

    public Player getPlayer2() {
        return player2;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
