package com.chessmasters.chessapi.request;

import com.chessmasters.chessapi.model.GameMove;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameRequest {

    private final Long playerId;
    private GameMove move;

    public GameRequest(@JsonProperty("playerId") Long playerId, @JsonProperty("move") GameMove move) {
        this.playerId = playerId;
        this.move = move;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public GameMove getMove() {
        return move;
    }
}
