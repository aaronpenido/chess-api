package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.MoveEntity;

public class Move {

    private Long gameId;
    private Piece piece;
    private Square destination;
    private int order;

    public Move(MoveEntity moveEntity) {
        if(moveEntity.getGame() != null) {
            this.gameId = moveEntity.getGame().getId();
            this.piece = new Piece(moveEntity.getPiece());
            this.order = moveEntity.getMoveOrder();
            this.destination = new Square(moveEntity.getDestination());
        }
    }

    public Long getGameId() {
        return gameId;
    }

    public Piece getPiece() {
        return piece;
    }

    public Square getDestination() {
        return destination;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
