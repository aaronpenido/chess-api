package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.move.StraightMove;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity(name = "Rook")
@DiscriminatorValue("Rook")
public class Rook extends Piece {

    public Rook() {
    }

    public Rook(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        return new StraightMove(board, square).path();
    }
}
