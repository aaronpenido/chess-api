package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.models.SquareModel;

public class MoveResponse {

    private Long gameId;
    private int order;
    private SquareModel destination;

    public MoveResponse(MoveModel moveModel) {
        this.gameId = moveModel.getGameId();
        this.destination = moveModel.getDestination();
        this.order = moveModel.getOrder();
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
