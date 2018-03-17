package com.chessmasters.chessapi.request;

import com.chessmasters.chessapi.models.SquareModel;

public class MoveRequest {

    private SquareModel destination;

    public MoveRequest() {
    }

    public MoveRequest(SquareModel destination) {
        this.destination = destination;
    }

    public SquareModel getDestination() {
        return destination;
    }
}
