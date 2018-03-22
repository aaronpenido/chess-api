package com.chessmasters.chessapi.request;

import com.chessmasters.chessapi.models.Piece;
import com.chessmasters.chessapi.models.Square;

public class MoveRequest {

    private Square destination;
    private Piece pieceModel;

    public MoveRequest() {
    }

    public MoveRequest(Piece pieceModel, Square destination) {
        this.pieceModel = pieceModel;
        this.destination = destination;
    }

    public Square getDestination() {
        return destination;
    }

    public Piece getPieceModel() {
        return pieceModel;
    }
}
