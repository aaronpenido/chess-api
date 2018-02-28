package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;

public class WhitePawnMove extends PawnMove {

    public WhitePawnMove(Board board, Square square) {
        super(board,
                square,
                2,
                8,
                square.getNumber() + 1,
                square.getNumber() + 2,
                Square::nextNumber);
    }
}
