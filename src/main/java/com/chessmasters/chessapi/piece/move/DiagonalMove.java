package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMove {

    private Square square;
    private boolean isOneMoveSquare;

    public DiagonalMove(Square square) {
        this.square = square;
        this.isOneMoveSquare = false;
    }

    public DiagonalMove(Square square, boolean isOneMoveSquare) {
        this.square = square;
        this.isOneMoveSquare = isOneMoveSquare;
    }

    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();

        moves.addAll(leftDiagonal());
        moves.addAll(rightDiagonal());

        return moves;
    }

    private List<Square> rightDiagonal() {
        final char rightBorderLetter = 'H';
        List<Square> diagonals = new ArrayList<>();

        if(square.getLetter() != rightBorderLetter) {
            int numberAhead = square.getNumber() + 1;
            int numberBehind = square.getNumber() - 1;
            final char nextLetter = (char)(square.getLetter() + 1);

            if(isOneMoveSquare) {
                return oneDiagonalMove(nextLetter, numberAhead, numberBehind);
            }

            for (int letter = (int)nextLetter; letter <= 72; letter++) {
                diagonals.addAll(oneDiagonalMove((char)letter, numberAhead, numberBehind));
                numberAhead++;
                numberBehind--;
            }
        }

        return diagonals;
    }

    private List<Square> leftDiagonal() {
        final char leftBorderLetter = 'A';
        List<Square> diagonals = new ArrayList<>();

        if(square.getLetter() != leftBorderLetter) {
            int numberAhead = square.getNumber() + 1;
            int numberBehind = square.getNumber() - 1;
            final char previousLetter = (char)(square.getLetter() - 1);

            if(isOneMoveSquare) {
                return oneDiagonalMove(previousLetter, numberAhead, numberBehind);
            }

            for (int letter = (int)previousLetter; letter >= 65; letter--) {
                diagonals.addAll(oneDiagonalMove((char)letter, numberAhead, numberBehind));
                numberAhead++;
                numberBehind--;
            }
        }

        return diagonals;
    }

    private List<Square> oneDiagonalMove(final char letter,
                                         final Integer numberAhead,
                                         final Integer numberBehind) {
        List<Square> diagonals = new ArrayList<>();

        if(numberAhead <= 8) {
            Square rightDiagonalAhead = new Square(letter, numberAhead);
            diagonals.add(rightDiagonalAhead);
        }

        if(numberBehind >= 1) {
            Square rightDiagonalBehind = new Square(letter, numberBehind);
            diagonals.add(rightDiagonalBehind);
        }

        return diagonals;
    }
}
