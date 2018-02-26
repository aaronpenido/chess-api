package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.move.DiagonalMove;
import com.chessmasters.chessapi.piece.move.StraightMove;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        List<Square> moves = new ArrayList<>();

        StraightMove straightMove = new StraightMove(board, square);
        DiagonalMove diagonalMove = new DiagonalMove(board, square);

        moves.addAll(straightMove.path());
        moves.addAll(diagonalMove.path());

        return moves;
    }
}
