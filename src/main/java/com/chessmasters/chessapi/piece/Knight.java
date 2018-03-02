package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.move.KnightMove;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity(name = "Knight")
@DiscriminatorValue("Knight")
public class Knight extends Piece {

    public Knight() {
    }

    public Knight(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        return new KnightMove(board, square).path();
    }
}
