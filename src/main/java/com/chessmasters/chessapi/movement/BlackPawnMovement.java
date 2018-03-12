package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.entity.Square;

public class BlackPawnMovement extends PawnMovement {

    public BlackPawnMovement(Board board, Square square) {
        super(board, square,7, Square::previousNumber);
    }
}
