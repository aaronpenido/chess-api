package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.enums.GameStatus;

public class GameResponse {

    private Long id;
    private GameStatus status;

    public GameResponse() {
    }

    public GameResponse(Long id, GameStatus status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }
}
