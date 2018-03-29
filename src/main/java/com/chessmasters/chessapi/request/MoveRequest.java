package com.chessmasters.chessapi.request;

import com.chessmasters.chessapi.models.Square;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveRequest {

    private Long playerId;
    private Long pieceId;
    private Square destination;

    public MoveRequest(@JsonProperty("playerId") Long playerId,
                       @JsonProperty("pieceId") Long pieceId,
                       @JsonProperty("destination") Square destination) {
        this.playerId = playerId;
        this.pieceId = pieceId;
        this.destination = destination;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getPieceId() {
        return pieceId;
    }

    public Square getDestination() {
        return destination;
    }
}
