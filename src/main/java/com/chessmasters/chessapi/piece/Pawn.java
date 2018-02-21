package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.*;
import com.chessmasters.chessapi.piece.move.BlackPawnMove;
import com.chessmasters.chessapi.piece.move.PawnMove;
import com.chessmasters.chessapi.piece.move.WhitePawnMove;

import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;

public class Pawn extends Piece {

    private PawnMove pawnMove;

    public Pawn(Color color, Square square) {
        super(color, square);

        if(color.equals(WHITE)) {
            pawnMove = new WhitePawnMove(square);
        } else {
            pawnMove = new BlackPawnMove(square);
        }
    }

    @Override
    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();
        final char nextLetter = (char) (square.getLetter() + 1);
        final char previousLetter = (char)(square.getLetter() - 1);

        if(square.getNumber() != pawnMove.getPromotionNumber()) {
            Square oneSquareAhead = new Square(square.getLetter(), pawnMove.getNextNumber());
            moves.add(oneSquareAhead);
        }

        if(square.getNumber() == pawnMove.getInitialNumber()) {
            Square twoSquaresAhead = new Square(square.getLetter(), pawnMove.getNextTwoNumber());
            moves.add(twoSquaresAhead);
        }

        if(square.getLetter() != 'H') {
            Square rightDiagonalSquare = new Square(nextLetter, pawnMove.getNextNumber());
            moves.add(rightDiagonalSquare);
        }
        if(square.getLetter() != 'A') {
            Square leftDiagonalSquare = new Square(previousLetter, pawnMove.getNextNumber());
            moves.add(leftDiagonalSquare);
        }

        return moves;
    }
}
