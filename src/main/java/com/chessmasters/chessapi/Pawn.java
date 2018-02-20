package com.chessmasters.chessapi;

import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;

public class Pawn {

    private Color color;
    private Square square;
    private PawnMove pawnMove;
    private List<Square> moves = new ArrayList<>();

    public Pawn(Color color, Square square) {
        this.color = color;
        this.square = square;

        if(color.equals(WHITE)) {
            pawnMove = new WhitePawnMove(square);
        } else {
            pawnMove = new BlackPawnMove(square);
        }
    }

    public List<Square> getMoves() {
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
