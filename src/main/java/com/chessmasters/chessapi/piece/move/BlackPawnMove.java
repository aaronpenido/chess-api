package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Square;

public class BlackPawnMove extends PawnMove {

    public BlackPawnMove(Square square) {
        super(square,
                7,
                1,
                square.getNumber() - 1,
                square.getNumber() - 2);
    }
}
