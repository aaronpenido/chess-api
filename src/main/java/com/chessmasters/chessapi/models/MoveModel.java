package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.Move;

public class MoveModel {

    private Long gameId;
    private int order;

    public MoveModel(Move move) {

        if(move.getGame() != null) {
            this.gameId = move.getGame().getId();
            this.order = move.getMoveOrder();
        }
    }

    public Long getGameId() {
        return gameId;
    }

    public int getOrder() {
        return order;
    }
}
