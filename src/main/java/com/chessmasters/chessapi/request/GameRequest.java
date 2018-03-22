package com.chessmasters.chessapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameRequest {

    private Long playerId;

    public GameRequest(@JsonProperty("playerId") Long playerId) {
        this.playerId = playerId;
    }

    public Long getPlayerId() {
        return playerId;
    }
}
