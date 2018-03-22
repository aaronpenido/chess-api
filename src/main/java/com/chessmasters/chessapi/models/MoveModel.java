package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.MoveEntity;

public class MoveModel {

    private Long gameId;
    private PieceModel pieceModel;
    private SquareModel destination;
    private int order;

    public MoveModel(MoveEntity move) {

        if(move.getGame() != null) {
            this.gameId = move.getGame().getId();
            this.pieceModel = new Pawn(move.getPiece());
            this.order = move.getMoveOrder();
            this.destination = new SquareModel(move.getDestination());
        }
    }

    public Long getGameId() {
        return gameId;
    }

    public PieceModel getPieceModel() {
        return pieceModel;
    }

    public SquareModel getDestination() {
        return destination;
    }

    public int getOrder() {
        return order;
    }
}
