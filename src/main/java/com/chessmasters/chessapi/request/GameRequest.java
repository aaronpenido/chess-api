package com.chessmasters.chessapi.request;

import com.chessmasters.chessapi.Square;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameRequest {

    private final Long playerId;
    private Square from;
    private Square destination;

    public GameRequest(@JsonProperty("playerId") Long playerId) {
        this.playerId = playerId;
    }

    public GameRequest(Long playerId, Square from, Square destination) {
        this.playerId = playerId;
        this.from = from;
        this.destination = destination;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Square getFrom() {
        return from;
    }

    public Square getDestination() {
        return destination;
    }
}
