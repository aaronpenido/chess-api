package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.models.MoveModel;

public class MoveResponse {

    private Long gameId;

    public MoveResponse(MoveModel moveModel) {
        this.gameId = moveModel.getGameId();
    }

    public Long getGameId() {
        return gameId;
    }
}
