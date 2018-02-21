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
                Square rightDiagonalAhead = new Square(nextLetter, numberAhead);
                Square rightDiagonalBehind = new Square(nextLetter, numberBehind);

                diagonals.add(rightDiagonalAhead);
                diagonals.add(rightDiagonalBehind);

                return diagonals;
            }

            for (int letter = (int)nextLetter; letter <= 72; letter++) {
                if(square.getNumber() < 8 && numberAhead <= 8) {
                    diagonals.add(new Square((char)letter, numberAhead));
                    numberAhead++;
                }

                if(square.getNumber() > 1 && numberBehind >= 1) {
                    diagonals.add(new Square((char)letter, numberBehind));
                    numberBehind--;
                }
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
                Square leftDiagonalAhead = new Square(previousLetter, numberAhead);
                Square leftDiagonalBehind = new Square(previousLetter, numberBehind);

                diagonals.add(leftDiagonalAhead);
                diagonals.add(leftDiagonalBehind);

                return diagonals;
            }

            for (int letter = (int)previousLetter; letter >= 65; letter--) {
                if(square.getNumber() < 8 && numberAhead <= 8) {
                    diagonals.add(new Square((char)letter, numberAhead));
                    numberAhead++;
                }

                if(square.getNumber() > 1 && numberBehind >= 1) {
                    diagonals.add(new Square((char)letter, numberBehind));
                    numberBehind--;
                }
            }
        }

        return diagonals;
    }
}
