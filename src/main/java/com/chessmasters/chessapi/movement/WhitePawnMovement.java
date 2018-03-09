package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;

public class WhitePawnMovement extends PawnMovement {

    public WhitePawnMovement(Board board, Square square) {
        super(board, square, 2, Square::nextNumber);
    }
}
