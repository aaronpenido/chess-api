package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DiagonalMove {

    private Square square;
    private boolean isOneSquarePerMove;

    public DiagonalMove(Square square) {
        this.square = square;
        this.isOneSquarePerMove = false;
    }

    public DiagonalMove(Square square, boolean isOneSquarePerMove) {
        this.square = square;
        this.isOneSquarePerMove = isOneSquarePerMove;
    }

    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();
        final Letter leftBorderLetter = Letter.A;
        final Letter rightBorderLetter = Letter.H;

        if(!square.getLetter().equals(leftBorderLetter)) {
            moves.addAll(leftDiagonal());
        }

        if(!square.getLetter().equals(rightBorderLetter)) {
            moves.addAll(rightDiagonal());
        }

        return moves;
    }

    public List<Square> path(Square destination) {
        List<Square> path = new ArrayList<>();

        if(!moves().contains(destination)) {
            return path;
        }

        final boolean isDestinationLetterPreviousThanSquareLetter =
                destination.getLetter().ordinal() < square.getLetter().ordinal();
        final boolean isDestinationNumberGreaterThanSquareNumber =
                destination.getNumber() > square.getNumber();

        Predicate<Square> predicate;

        if(isDestinationNumberGreaterThanSquareNumber) {
            predicate = filterAllSquaresFromAheadSquareToDestination(destination);
        } else {
            predicate = filterAllSquaresFromBehindASquareToDestination(destination);
        }

        if(isDestinationLetterPreviousThanSquareLetter) {
            path = leftDiagonal();
        } else {
            path = rightDiagonal();
        }

        return path.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private List<Square> leftDiagonal() {
        final Letter previousLetter = Letter.previousLetter(square.getLetter());
        final List<Letter> previousLetters = Letter.previousLetters(square.getLetter());

        return allDiagonalsFromStartingLetterInsideLetterList(previousLetter, previousLetters);
    }

    private List<Square> rightDiagonal() {
        final Letter nextLetter = Letter.nextLetter(square.getLetter());
        final List<Letter> nextLetters = Letter.nextLetters(square.getLetter());

        return allDiagonalsFromStartingLetterInsideLetterList(nextLetter, nextLetters);
    }

    private List<Square> allDiagonalsFromStartingLetterInsideLetterList(final Letter startingLetter,
                                                                        final List<Letter> letterList) {
        int numberAhead = square.getNumber() + 1;
        int numberBehind = square.getNumber() - 1;

        if(isOneSquarePerMove) {
            return oneSquarePerDiagonal(startingLetter, numberAhead, numberBehind);
        }

        List<Square> diagonals = new ArrayList<>();
        for (Letter l : letterList) {
            diagonals.addAll(oneSquarePerDiagonal(l, numberAhead, numberBehind));
            numberAhead++;
            numberBehind--;
        }

        return diagonals;
    }

    private List<Square> oneSquarePerDiagonal(final Letter letter,
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

    private Predicate<Square> filterAllSquaresFromBehindASquareToDestination(Square destination) {
        return s -> s.getNumber() < square.getNumber() &&
                s.getNumber() > destination.getNumber();
    }

    private Predicate<Square> filterAllSquaresFromAheadSquareToDestination(Square destination) {
        return s -> s.getNumber() > square.getNumber() &&
                s.getNumber() < destination.getNumber();
    }
}
