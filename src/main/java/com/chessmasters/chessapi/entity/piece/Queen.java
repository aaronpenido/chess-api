package com.chessmasters.chessapi.entity.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.movement.DiagonalMovement;
import com.chessmasters.chessapi.movement.StraightMovement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Queen")
@DiscriminatorValue("Queen")
public class Queen extends Piece {

    public Queen() {
    }

    public Queen(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves(Board board) {
        List<Square> moves = new ArrayList<>();

        StraightMovement straightMove = new StraightMovement(board, square);
        DiagonalMovement diagonalMove = new DiagonalMovement(board, square);

        moves.addAll(straightMove.path());
        moves.addAll(diagonalMove.path());

        return moves;
    }
}
