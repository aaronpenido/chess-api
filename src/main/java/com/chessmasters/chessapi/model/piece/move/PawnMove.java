package com.chessmasters.chessapi.model.piece.move;

import com.chessmasters.chessapi.model.Coordinate;

public abstract class PawnMove {

    private Coordinate actualCoordinate;

    public PawnMove(Coordinate actualCoordinate) {
        this.actualCoordinate = actualCoordinate;
    }

    public abstract int getInitialNumber();
    public abstract int getMaxNumber();
    public abstract int getNextNumber();
    public abstract int getNextTwoNumbers();

    public Coordinate getActualCoordinate() {
        return actualCoordinate;
    }
}
