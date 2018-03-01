package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.move.DiagonalMove;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        return new DiagonalMove(board, square).path();
    }
}
