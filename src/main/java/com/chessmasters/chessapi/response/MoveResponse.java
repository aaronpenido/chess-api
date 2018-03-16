package com.chessmasters.chessapi.response;

public class MoveResponse {

    private Long gameId;

    public MoveResponse(Long gameId) {
        this.gameId = gameId;
    }

    public Long getGameId() {
        return gameId;
    }
}
