package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.models.PieceModel;
import com.chessmasters.chessapi.models.SquareModel;

public class MoveResponse {

    private Long gameId;
    private PieceModel pieceModel;
    private int order;
    private SquareModel destination;

    public MoveResponse(MoveModel moveModel) {
        this.gameId = moveModel.getGameId();
        this.pieceModel = moveModel.getPieceModel();
        this.destination = moveModel.getDestination();
        this.order = moveModel.getOrder();
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
