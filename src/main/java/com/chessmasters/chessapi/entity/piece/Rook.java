package com.chessmasters.chessapi.entity.piece;

import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.movement.StraightMovement;

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
        return new StraightMovement(board, square).path();
    }
}
