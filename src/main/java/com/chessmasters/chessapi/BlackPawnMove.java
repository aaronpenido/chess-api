package com.chessmasters.chessapi;

public class BlackPawnMove extends PawnMove {
    public BlackPawnMove(Square square) {
        super(square,
                7,
                1,
                square.getNumber() - 1,
                square.getNumber() - 2);
    }
}
