package com.chessmasters.chessapi.piece;

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
    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();

        StraightMove straightMove = new StraightMove(square);
        DiagonalMove diagonalMove = new DiagonalMove(square);

        moves.addAll(straightMove.moves());
        moves.addAll(diagonalMove.moves());

        return moves;
    }
}
