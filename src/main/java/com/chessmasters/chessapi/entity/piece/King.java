package com.chessmasters.chessapi.entity.piece;

import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.movement.KingMovement;

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
        return new KingMovement(board, square).path();
    }
}
