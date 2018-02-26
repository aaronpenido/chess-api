package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.move.DiagonalMove;
import com.chessmasters.chessapi.piece.move.KingMove;
import com.chessmasters.chessapi.piece.move.StraightMove;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    public King(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        List<Square> moves = new ArrayList<>();

        //moves.addAll(new StraightMove(board, square, true).path());
        //moves.addAll(new DiagonalMove(board, square, true).path());

        return new KingMove(board, square).path();
    }
}
