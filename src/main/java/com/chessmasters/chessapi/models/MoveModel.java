package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.Move;

public class MoveModel {

    private Long gameId;
    private SquareModel destination;
    private int order;

    public MoveModel(Move move) {

        if(move.getGame() != null) {
            this.gameId = move.getGame().getId();
            this.order = move.getMoveOrder();
            this.destination = new SquareModel(move.getDestination());
        }
    }

    public Long getGameId() {
        return gameId;
    }

    public SquareModel getDestination() {
        return destination;
    }

    public int getOrder() {
        return order;
    }
}
