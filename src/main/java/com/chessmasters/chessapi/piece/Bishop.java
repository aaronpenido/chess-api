package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.movement.DiagonalMovement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity(name = "Bishop")
@DiscriminatorValue("Bishop")
public class Bishop extends Piece {

    public Bishop() {
    }

    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        return new DiagonalMovement(board, square).path();
    }
}
