package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.move.KingMove;
import java.util.List;

public class King extends Piece{

    public King(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        return new KingMove(board, square).path();
    }
}
