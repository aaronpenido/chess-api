package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.move.KingMove;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity(name = "King")
@DiscriminatorValue("King")
public class King extends Piece{

    public King() {
    }

    public King(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        return new KingMove(board, square).path();
    }
}
