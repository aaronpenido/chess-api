package com.chessmasters.chessapi.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;

public class WhitePawnMove extends PawnMove {

    public WhitePawnMove(Board board, Square square) {
        super(board, square, 2, Square::nextNumber);
    }
}
