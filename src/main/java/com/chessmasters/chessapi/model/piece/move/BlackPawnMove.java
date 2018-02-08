package com.chessmasters.chessapi.model.piece.move;

import com.chessmasters.chessapi.model.Coordinate;

public class BlackPawnMove extends PawnMove {

    private static final int INITIAL_NUMBER = 7;
    private static final int MAX_NUMBER = 1;

    public BlackPawnMove(Coordinate actualCoordinate) {
        super(actualCoordinate);
    }

    @Override
    public int getInitialNumber() {
        return INITIAL_NUMBER;
    }

    @Override
    public int getMaxNumber() {
        return MAX_NUMBER;
    }

    @Override
    public int getNextNumber() {
        return getActualCoordinate().getNumber() - 1;
    }

    @Override
    public int getNextTwoNumbers() {
        return getActualCoordinate().getNumber() - 2;
    }
}
