package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.entity.Square;

public class WhitePawnMovement extends PawnMovement {

    public WhitePawnMovement(Board board, Square square) {
        super(board, square, 2, Square::nextNumber);
    }
}
