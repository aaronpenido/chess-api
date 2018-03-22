package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.MoveEntity;

public class Move {

    private Long gameId;
    private Piece pieceModel;
    private Square destination;
    private int order;

    public Move(MoveEntity moveEntity) {

        if(moveEntity.getGame() != null) {
            this.gameId = moveEntity.getGame().getId();
            this.pieceModel = new Pawn(moveEntity.getPiece());
            this.order = moveEntity.getMoveOrder();
            this.destination = new Square(moveEntity.getDestination());
        }
    }

    public Long getGameId() {
        return gameId;
    }

    public Piece getPieceModel() {
        return pieceModel;
    }

    public Square getDestination() {
        return destination;
    }

    public int getOrder() {
        return order;
    }
}
