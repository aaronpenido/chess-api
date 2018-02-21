package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StraightMove {

    private Square square;
    private boolean isOneMoveSquare;

    public StraightMove(Square square) {
        this.square = square;
    }

    public StraightMove(Square square, boolean isOneMoveSquare) {
        this.square = square;
        this.isOneMoveSquare = isOneMoveSquare;
    }

    public List<Square> moves() {
        if(isOneMoveSquare) {
            return oneMoveSquare(square.getLetter(), square.getNumber());
        }

        List<Square> moves = new ArrayList<>();
        for (int letter = 65; letter <= 72; letter++) {
            moves.addAll(oneMoveSquare((char)letter, square.getNumber()));
        }

        for (int number = 1; number <= 8; number++) {
            moves.addAll(oneMoveSquare(square.getLetter(), number));
        }

        moves.removeAll(Arrays.asList(square));

        return moves;
    }

    private List<Square> oneMoveSquare(final char letter, final int number) {

        List<Square> moves = new ArrayList<>();
        final int downBorderNumber = 1;
        final int topBorderNumber = 8;
        final int previousNumber = number - 1;
        final int nextNumber = number + 1;

        if(number != topBorderNumber) {
            Square ahead = new Square(letter, nextNumber);
            moves.add(ahead);
        }

        if(number != downBorderNumber) {
            Square behind = new Square(letter, previousNumber);
            moves.add(behind);
        }

        if(letter != 'A') {
            final char previousLetter = (char)(letter - 1);

            Square left = new Square(previousLetter, number);
            moves.add(left);
        }

        if(letter != 'H') {
            final char nextLetter = (char)(letter + 1);

            Square right = new Square(nextLetter, number);
            moves.add(right);
        }

        return moves;
    }
}
