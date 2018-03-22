package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.models.Move;
import com.chessmasters.chessapi.models.Piece;
import com.chessmasters.chessapi.models.Square;

public class MoveResponse {

    private Long gameId;
    private Piece pieceModel;
    private int order;
    private Square destination;

    public MoveResponse(Move moveModel) {
        this.gameId = moveModel.getGameId();
        this.pieceModel = moveModel.getPieceModel();
        this.destination = moveModel.getDestination();
        this.order = moveModel.getOrder();
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
