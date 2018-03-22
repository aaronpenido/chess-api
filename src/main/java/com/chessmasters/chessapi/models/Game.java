package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

    private Long id;
    private GameStatus status;
    private Long playerId;
    private Long player2Id;

    public Game(@JsonProperty("gameEntity") GameEntity gameEntity) {
        this.id = gameEntity.getId();
        this.status = gameEntity.getStatus();
        this.playerId = gameEntity.getPlayer().getId();

        if(gameEntity.getPlayer2() != null) {
            this.player2Id = gameEntity.getPlayer2().getId();
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
