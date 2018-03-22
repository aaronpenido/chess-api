package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.Game;

public class GameResponse {

    private Long id;
    private GameStatus status;
    private Long playerId;
    private Long player2Id;

    public GameResponse() {
    }

    public GameResponse(Game gameModel) {
        this.id = gameModel.getId();
        this.status = gameModel.getStatus();
        this.playerId = gameModel.getPlayerId();
        this.player2Id = gameModel.getPlayer2Id();
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
