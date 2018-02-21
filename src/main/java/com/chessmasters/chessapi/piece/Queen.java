package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Square;
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
        Bishop bishop = new Bishop(color, square);

        moves.addAll(straightMove.moves());
        moves.addAll(bishop.moves());

        return moves;
    }
}
