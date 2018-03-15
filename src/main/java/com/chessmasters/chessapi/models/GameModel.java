package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.enums.GameStatus;

public class GameModel {

    private Long id;
    private GameStatus status;
    private Long playerId;
    private Long player2Id;

    public GameModel() {
    }

    public GameModel(Game game) {
        this.id = game.getId();
        this.status = game.getStatus();
        this.playerId = game.getPlayer().getId();
        if(game.getPlayer2() != null) {
            this.player2Id = game.getPlayer2().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }
}
