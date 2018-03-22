package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.Game;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResponse {

    private Long id;
    private GameStatus status;
    private Long playerId;
    private Long player2Id;

    public GameResponse(@JsonProperty("game") Game game) {
        this.id = game.getId();
        this.status = game.getStatus();
        this.playerId = game.getPlayerId();
        this.player2Id = game.getPlayer2Id();
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
