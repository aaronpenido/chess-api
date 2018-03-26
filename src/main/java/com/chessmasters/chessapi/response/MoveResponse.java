package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.models.Move;
import com.chessmasters.chessapi.models.Piece;
import com.chessmasters.chessapi.models.Square;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveResponse {

    private Long gameId;
    private Piece piece;
    private int order;
    private Square destination;

    public MoveResponse(@JsonProperty("move") Move move) {
        this.gameId = move.getGameId();
        this.piece = move.getPieceModel();
        this.destination = move.getDestination();
        this.order = move.getOrder();
    }

    public Long getGameId() {
        return gameId;
    }

    public Piece getPiece() {
        return piece;
    }

    public Square getDestination() {
        return destination;
    }

    public int getOrder() {
        return order;
    }
}
