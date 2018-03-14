package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.enums.GameStatus;

public class GameModel {

    private Long id;
    private GameStatus status;

    public GameModel() {
    }

    public GameModel(Game game) {
        this.id = game.getId();
        this.status = game.getStatus();
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }
}
