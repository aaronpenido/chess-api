package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.models.MoveModel;

public class MoveResponse {

    private Long gameId;
    private int order;

    public MoveResponse(MoveModel moveModel) {
        this.gameId = moveModel.getGameId();
        this.order = moveModel.getOrder();
    }

    public Long getGameId() {
        return gameId;
    }

    public int getOrder() {
        return order;
    }
}
