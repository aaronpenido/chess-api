package com.chessmasters.chessapi;

public class Move {

    private final Square origin;
    private final Square destination;

    public Move(Square origin, Square destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Square getOrigin() {
        return origin;
    }

    public Square getDestination() {
        return destination;
    }
}
