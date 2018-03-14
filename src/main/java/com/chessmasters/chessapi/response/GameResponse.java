package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.GameModel;

public class GameResponse {

    private Long id;
    private GameStatus status;

    public GameResponse() {
    }

    public GameResponse(GameModel gameModel) {
        this.id = gameModel.getId();
        this.status = gameModel.getStatus();
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }
}
