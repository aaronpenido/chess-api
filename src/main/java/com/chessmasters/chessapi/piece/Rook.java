package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Piece;
import com.chessmasters.chessapi.piece.move.StraightMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves() {
        return new ArrayList<>();
    }

    @Override
    public List<Square> moves(Board board) {
        return new StraightMove(board, square).path();
    }
}
