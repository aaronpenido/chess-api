package com.chessmasters.chessapi;

public class WhitePawnMove extends PawnMove {

    public WhitePawnMove(Square square) {
        super(square,
                2,
                8,
                square.getNumber() + 1,
                square.getNumber() + 2);
    }
}
