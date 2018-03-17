package com.chessmasters.chessapi.request;

import com.chessmasters.chessapi.models.PieceModel;
import com.chessmasters.chessapi.models.SquareModel;

public class MoveRequest {

    private SquareModel destination;
    private PieceModel pieceModel;

    public MoveRequest() {
    }

    public MoveRequest(PieceModel pieceModel, SquareModel destination) {
        this.pieceModel = pieceModel;
        this.destination = destination;
    }

    public SquareModel getDestination() {
        return destination;
    }

    public PieceModel getPieceModel() {
        return pieceModel;
    }
}
