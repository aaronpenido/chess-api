package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.move.DiagonalMove;
import com.chessmasters.chessapi.piece.move.StraightMove;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    public King(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();

        moves.addAll(new StraightMove(square, true).moves());
        moves.addAll(new DiagonalMove(square, true).moves());

        return moves;
    }
}
