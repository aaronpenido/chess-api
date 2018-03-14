package com.chessmasters.chessapi.request;

public class GameRequest {

    private Long playerId;

    public GameRequest() {
    }

    public GameRequest(Long playerId) {
        this.playerId = playerId;
    }

    public Long getPlayerId() {
        return playerId;
    }
}
