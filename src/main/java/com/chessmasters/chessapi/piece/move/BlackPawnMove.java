package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;

public class BlackPawnMove extends PawnMove {

    public BlackPawnMove(Board board, Square square) {
        super(board, square,7, Square::previousNumber);
    }
}
