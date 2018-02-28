package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.*;
import com.chessmasters.chessapi.piece.move.BlackPawnMove;
import com.chessmasters.chessapi.piece.move.PawnMove;
import com.chessmasters.chessapi.piece.move.WhitePawnMove;

import java.util.List;

import static com.chessmasters.chessapi.Color.*;

public class Pawn extends Piece {

    private PawnMove pawnMove;

    public Pawn(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        if(color.equals(WHITE)) {
            pawnMove = new WhitePawnMove(board, square);
        } else {
            pawnMove = new BlackPawnMove(board, square);
        }

        return pawnMove.path();
    }

    public List<Square> attackMoves(Board board) {
        if(color.equals(WHITE)) {
            pawnMove = new WhitePawnMove(board, square);
        } else {
            pawnMove = new BlackPawnMove(board, square);
        }

        return pawnMove.attackMoves();
    }
}
