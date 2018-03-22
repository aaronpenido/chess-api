package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameModel {

    private Long id;
    private GameStatus status;
    private Long playerId;
    private Long player2Id;

    public GameModel(@JsonProperty("game") GameEntity game) {
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
