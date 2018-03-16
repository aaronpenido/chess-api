package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.Move;

public class MoveModel {

    private Long gameId;

    public MoveModel(Move move) {

        if(move.getGame() != null) {
            this.gameId = move.getGame().getId();
        }
    }

    public Long getGameId() {
        return gameId;
    }
}
