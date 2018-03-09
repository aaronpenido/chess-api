package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.*;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.movement.BlackPawnMovement;
import com.chessmasters.chessapi.movement.PawnMovement;
import com.chessmasters.chessapi.movement.WhitePawnMovement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

import static com.chessmasters.chessapi.enums.Color.*;

@Entity(name = "Pawn")
@DiscriminatorValue("Pawn")
public class Pawn extends Piece {

    @Transient
    private PawnMovement pawnMove;

    public Pawn() {
    }

    public Pawn(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        if(color.equals(WHITE)) {
            pawnMove = new WhitePawnMovement(board, square);
        } else {
            pawnMove = new BlackPawnMovement(board, square);
        }

        return pawnMove.path();
    }

    public List<Square> attackMoves(Board board) {
        if(color.equals(WHITE)) {
            pawnMove = new WhitePawnMovement(board, square);
        } else {
            pawnMove = new BlackPawnMovement(board, square);
        }

        return pawnMove.attackMoves();
    }
}
