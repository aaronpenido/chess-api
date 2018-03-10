package com.chessmasters.chessapi.request;

import com.chessmasters.chessapi.Move;
import com.chessmasters.chessapi.Square;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameRequest {

    private final Long playerId;
    private Move move;

    public GameRequest(@JsonProperty("playerId") Long playerId, @JsonProperty("move") Move move) {
        this.playerId = playerId;
        this.move = move;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Move getMove() {
        return move;
    }
}
