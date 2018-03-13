package com.chessmasters.chessapi.entity.piece;

import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.movement.BlackPawnMovement;
import com.chessmasters.chessapi.movement.PawnMovement;
import com.chessmasters.chessapi.movement.WhitePawnMovement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

import static com.chessmasters.chessapi.enums.Color.*;

@Entity(name = "Pawn")
@DiscriminatorValue("Pawn")
public class Pawn extends Piece {

    public Pawn() {
    }

    public Pawn(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        return createPawnMove(board).path();
    }

    public List<Square> attackMoves(Board board) {
        return createPawnMove(board).attackMoves();
    }

    private PawnMovement createPawnMove(Board board) {
        if(color.equals(WHITE)) {
            return new WhitePawnMovement(board, square);
        }
        return new BlackPawnMovement(board, square);
    }
}
