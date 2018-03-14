package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.enums.GameStatus;

public class GameResponse {

    private Long id;
    private GameStatus status;

    public GameResponse() {
    }

    public GameResponse(GameStatus status) {
        this.status = status;
    }

    public GameStatus getStatus() {
        return status;
    }
}
