package com.chessmasters.chessapi;

import java.util.ArrayList;
import java.util.List;

public class King {

    private Color color;
    private Square square;
    private int previousNumber;
    private int nextNumber;

    public King(Color color, Square square) {
        this.color = color;
        this.square = square;

        previousNumber = square.getNumber() - 1;
        nextNumber = square.getNumber() + 1;
    }

    public Color getColor() {
        return color;
    }

    public Square getSquare() {
        return square;
    }

    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();
        final int downBorderNumber = 1;
        final int topBorderNumber = 8;

        if(square.getNumber() != topBorderNumber) {
            Square ahead = new Square(square.getLetter(), nextNumber);
            moves.add(ahead);
        }

        if(square.getNumber() != downBorderNumber) {
            Square behind = new Square(square.getLetter(), previousNumber);
            moves.add(behind);
        }

        moves.addAll(leftSquares());
        moves.addAll(rightSquares());

        return moves;
    }

    private List<Square> leftSquares() {
        final List<Square> moves = new ArrayList<>();
        final char leftBorderLetter = 'A';
        final char previousLetter = (char)(square.getLetter() - 1);

        if(square.getLetter() != leftBorderLetter) {
            Square left = new Square(previousLetter, square.getNumber());
            Square leftDiagonalAhead = new Square(previousLetter, nextNumber);
            Square leftDiagonalBehind = new Square(previousLetter, previousNumber);

            moves.add(left);
            moves.add(leftDiagonalAhead);
            moves.add(leftDiagonalBehind);
        }

        return moves;
    }

    private List<Square> rightSquares() {
        final List<Square> moves = new ArrayList<>();
        final char rightBorderLetter = 'H';
        final char nextLetter = (char) (square.getLetter() + 1);

        if(square.getLetter() != rightBorderLetter) {
            Square right = new Square(nextLetter, square.getNumber());
            Square rightDiagonalAhead = new Square(nextLetter, nextNumber);
            Square rightDiagonalBehind = new Square(nextLetter, previousNumber);

            moves.add(right);
            moves.add(rightDiagonalAhead);
            moves.add(rightDiagonalBehind);
        }

        return moves;
    }
}
