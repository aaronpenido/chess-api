package com.chessmasters.chessapi;

import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;

public class Pawn {

    private Color color;
    private Square square;
    private List<Square> moves = new ArrayList<>();

    public Pawn(Color color, Square square) {
        this.color = color;
        this.square = square;
    }

    public List<Square> getMoves() {
        final char nextLetter = (char) (square.getLetter() + 1);
        final int nextNumber;
        final int twoNextNumber;
        final char previousLetter = (char)(square.getLetter() - 1);

        if(color.equals(WHITE)) {
            nextNumber = square.getNumber() + 1;
            twoNextNumber = nextNumber + 1;
        } else {
            nextNumber = square.getNumber() - 1;
            twoNextNumber = nextNumber - 1;
        }

        if(square.getNumber() != 1 && square.getNumber() != 8) {
            Square oneSquareAhead = new Square(square.getLetter(), nextNumber);
            moves.add(oneSquareAhead);
        }

        if((color.equals(WHITE) && square.getNumber() == 2) || color.equals(BLACK) && square.getNumber() == 7) {
            Square twoSquaresAhead = new Square(square.getLetter(), twoNextNumber);
            moves.add(twoSquaresAhead);
        }

        if(square.getLetter() != 'H') {
            Square rightDiagonalSquare = new Square(nextLetter, nextNumber);
            moves.add(rightDiagonalSquare);
        }
        if(square.getLetter() != 'A') {
            Square leftDiagonalSquare = new Square(previousLetter, nextNumber);
            moves.add(leftDiagonalSquare);
        }

        return moves;
    }
}
