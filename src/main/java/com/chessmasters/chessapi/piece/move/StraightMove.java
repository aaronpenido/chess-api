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
        List<Square> moves = new ArrayList<>();

        if(isOneMoveSquare) {
            return oneMoveSquare();
        }

        for (int letter = 65; letter <= 72; letter++) {
            moves.add(new Square((char)letter, square.getNumber()));
        }

        for (int i = 1; i <= 8; i++) {
            moves.add(new Square(square.getLetter(), i));
        }
        moves.removeAll(Arrays.asList(square));

        return moves;
    }

    private List<Square> oneMoveSquare() {

        List<Square> moves = new ArrayList<>();
        final int downBorderNumber = 1;
        final int topBorderNumber = 8;
        final int previousNumber = square.getNumber() - 1;
        final int nextNumber = square.getNumber() + 1;

        if(square.getNumber() != topBorderNumber) {
            Square ahead = new Square(square.getLetter(), nextNumber);
            moves.add(ahead);
        }

        if(square.getNumber() != downBorderNumber) {
            Square behind = new Square(square.getLetter(), previousNumber);
            moves.add(behind);
        }

        if(square.getLetter() != 'A') {
            final char previousLetter = (char)(square.getLetter() - 1);

            Square left = new Square(previousLetter, square.getNumber());
            moves.add(left);
        }

        if(square.getLetter() != 'H') {
            final char nextLetter = (char) (square.getLetter() + 1);

            Square right = new Square(nextLetter, square.getNumber());
            moves.add(right);
        }

        return moves;
    }
}
