package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;

import java.util.List;

public abstract class Move {

    Board board;
    Square square;

    public Move(Board board, Square square) {
        this.board = board;
        this.square = square;
    }

    public abstract List<Square> path();
}
