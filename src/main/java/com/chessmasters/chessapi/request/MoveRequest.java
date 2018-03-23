package com.chessmasters.chessapi.request;

import com.chessmasters.chessapi.models.Piece;
import com.chessmasters.chessapi.models.Square;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveRequest {

    private Long playerId;
    private Square destination;
    private Piece pieceModel;

    public MoveRequest(@JsonProperty("playerId") Long playerId,
                       @JsonProperty("piece") Piece piece,
                       @JsonProperty("destination") Square destination) {
        this.playerId = playerId;
        this.pieceModel = piece;
        this.destination = destination;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Square getDestination() {
        return destination;
    }

    public Piece getPieceModel() {
        return pieceModel;
    }
}
