package com.chessmasters.chessapi.entity.piece;

import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.movement.KnightMovement;

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
        return new KnightMovement(board, square).path();
    }
}
