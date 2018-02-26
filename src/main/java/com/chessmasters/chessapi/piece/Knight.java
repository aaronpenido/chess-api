package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, Square square) {
        super(color, square);
    }

    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();

        moves.addAll(leftAhead());
        moves.addAll(leftBehind());
        moves.addAll(rightAhead());
        moves.addAll(rightBehind());

        return moves;
    }

    @Override
    public List<Square> moves(Board board) {
        return moves();
    }

    private List<Square> leftAhead() {
        List<Square> moves = new ArrayList<>();
        final Letter leftBorderLetter = Letter.A;
        final int topBorderNumber = 8;

        if(!square.getLetter().equals(leftBorderLetter) && square.getNumber() != topBorderNumber) {
            final Letter previousLetter = Letter.previousLetter(square.getLetter());
            final int nextNumber = square.getNumber() + 1;
            final int twoNextNumbers = nextNumber + 1;

            if(!previousLetter.equals(leftBorderLetter)) {
                final Letter twoPreviousLetters = Letter.previousLetter(previousLetter);
                moves.add(new Square(twoPreviousLetters, nextNumber));
            }

            if(twoNextNumbers <= 8) {
                moves.add(new Square(previousLetter, twoNextNumbers));
            }
        }

        return moves;
    }

    private List<Square> leftBehind() {
        List<Square> moves = new ArrayList<>();
        final Letter leftBorderLetter = Letter.A;
        final int bottomBorderNumber = 1;

        if(!square.getLetter().equals(leftBorderLetter) && square.getNumber() != bottomBorderNumber) {
            final Letter previousLetter = Letter.previousLetter(square.getLetter());
            final int previousNumber = square.getNumber() - 1;
            final int twoPreviousNumbers = previousNumber - 1;

            if(!previousLetter.equals(leftBorderLetter)) {
                final Letter twoPreviousLetters = Letter.previousLetter(previousLetter);
                moves.add(new Square(twoPreviousLetters, previousNumber));
            }

            if(twoPreviousNumbers > 0) {
                moves.add(new Square(previousLetter, twoPreviousNumbers));
            }
        }

        return moves;
    }

    private List<Square> rightAhead() {
        List<Square> moves = new ArrayList<>();
        final Letter rightBorderLetter = Letter.H;
        final int topBorderNumber = 8;

        if(!square.getLetter().equals(rightBorderLetter) && square.getNumber() != topBorderNumber) {
            final Letter nextLetter = Letter.nextLetter(square.getLetter());
            final int nextNumber = square.getNumber() + 1;
            final int twoNextNumbers = nextNumber + 1;

            if(!nextLetter.equals(rightBorderLetter)) {
                final Letter twoNextLetters = Letter.nextLetter(nextLetter);
                moves.add(new Square(twoNextLetters, nextNumber));
            }

            if(twoNextNumbers <= 8) {
                moves.add(new Square(nextLetter, twoNextNumbers));
            }
        }

        return moves;
    }

    private List<Square> rightBehind() {
        List<Square> moves = new ArrayList<>();
        final Letter rightBorderLetter = Letter.H;
        final int bottomBorderNumber = 1;

        if(!square.getLetter().equals(rightBorderLetter) && square.getNumber() != bottomBorderNumber) {
            final Letter nextLetter = Letter.nextLetter(square.getLetter());
            final int previousNumber = square.getNumber() - 1;
            final int twoPreviousNumbers = previousNumber - 1;

            if(!nextLetter.equals(rightBorderLetter)) {
                final Letter twoNextLetters = Letter.nextLetter(nextLetter);
                moves.add(new Square(twoNextLetters, previousNumber));
            }

            if(twoPreviousNumbers > 0) {
                moves.add(new Square(nextLetter, twoPreviousNumbers));
            }
        }

        return moves;
    }
}
