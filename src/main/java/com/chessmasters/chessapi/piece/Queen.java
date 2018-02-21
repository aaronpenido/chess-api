package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();

        Rook rook = new Rook(color, square);
        Bishop bishop = new Bishop(color, square);

        moves.addAll(rook.moves());
        moves.addAll(bishop.moves());

        return moves;
    }
}
