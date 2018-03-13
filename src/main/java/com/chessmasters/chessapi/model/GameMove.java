package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.entity.Square;

public class GameMove {

    private Square origin;
    private Square destination;

    public GameMove() {
    }

    public GameMove(Square origin, Square destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Square getOrigin() {
        return origin;
    }

    public Square getDestination() {
        return destination;
    }

    public void setOrigin(Square origin) {
        this.origin = origin;
    }

    public void setDestination(Square destination) {
        this.destination = destination;
    }
}
