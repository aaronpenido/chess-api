package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;

import static com.chessmasters.chessapi.model.Coordinate.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int BLACK_INITIAL_NUMBER = 7;
    private static final int WHITE_INITIAL_NUMBER = 2;
    private static final int BLACK_MAX_NUMBER = 1;
    private static final int WHITE_MAX_NUMBER = 8;

    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    void move() {

    }

    @Override
    public List<Coordinate> getValidCoordinates(Coordinate actualCoordinate) {
        if(this.getColor().equals(PieceColor.WHITE)) {
            return getValidWhiteCoordinates(actualCoordinate);
        }
        return getValidBlackCoordinates(actualCoordinate);
    }

    private List<Coordinate> getValidWhiteCoordinates(Coordinate actualCoordinate) {
        final int nextNumber = actualCoordinate.getNumber() + 1;
        final int nextTwoNumbers = actualCoordinate.getNumber() + 2;

        return getValidCoordinatesFromBothColors(actualCoordinate,
                WHITE_INITIAL_NUMBER,
                WHITE_MAX_NUMBER,
                nextNumber,
                nextTwoNumbers);
    }

    private List<Coordinate> getValidBlackCoordinates(Coordinate actualCoordinate) {
        final int nextNumber = actualCoordinate.getNumber() - 1;
        final int nextTwoNumbers = actualCoordinate.getNumber() - 2;

        return getValidCoordinatesFromBothColors(actualCoordinate,
                BLACK_INITIAL_NUMBER,
                BLACK_MAX_NUMBER,
                nextNumber,
                nextTwoNumbers);
    }

    private List<Coordinate> getValidCoordinatesFromBothColors(Coordinate actualCoordinate,
                                                               final int initialNumber,
                                                               final int maxNumber,
                                                               final int nextNumber,
                                                               final int nextTwoNumbers) {
        final int actualNumber = actualCoordinate.getNumber();
        final List<Coordinate> coordinates = new ArrayList<>();

        if(actualNumber == maxNumber) {
            return coordinates;
        }

        final Coordinate nextSquareCoordinate = new Coordinate(actualCoordinate.getLetter(), nextNumber);
        coordinates.add(nextSquareCoordinate);
        coordinates.addAll(nextTwoSquaresCoordinate(actualCoordinate, initialNumber, nextTwoNumbers));
        coordinates.addAll(leftDiagonalCoordinate(actualCoordinate, nextNumber));
        coordinates.addAll(rightDiagonalCoordinate(actualCoordinate, nextNumber));

        return coordinates;
    }

    private List<Coordinate> nextTwoSquaresCoordinate(final Coordinate actualCoordinate,
                                                      final int initialNumber,
                                                      final int nextTwoNumbers) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final int actualNumber = actualCoordinate.getNumber();
        final LetterFile actualLetter = actualCoordinate.getLetter();

        if(actualNumber == initialNumber) {
            coordinates.add(new Coordinate(actualLetter, nextTwoNumbers));
        }
        return coordinates;
    }

    private List<Coordinate> leftDiagonalCoordinate(final Coordinate actualCoordinate, final int nextNumber) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final int letterIndex = actualCoordinate.getLetter().ordinal();
        final LetterFile[] letterValues = Coordinate.LetterFile.values();

        final boolean isLetterNotOnLeftBorder = letterIndex >= 1;
        if(isLetterNotOnLeftBorder) {
            final LetterFile leftLetter = letterValues[letterIndex - 1];
            coordinates.add(new Coordinate(leftLetter, nextNumber));
        }

        return coordinates;
    }

    private List<Coordinate> rightDiagonalCoordinate(final Coordinate actualCoordinate, final int nextNumber) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final int letterIndex = actualCoordinate.getLetter().ordinal();
        final LetterFile[] letterValues = Coordinate.LetterFile.values();
        final boolean isLetterNotOnRightBorder = letterIndex < letterValues.length - 1;

        if(isLetterNotOnRightBorder) {
            final LetterFile rightLetter = letterValues[letterIndex + 1];
            coordinates.add(new Coordinate(rightLetter, nextNumber));
        }

        return coordinates;
    }

    @Override
    public String toString() {
        return String.format("%s Pawn", getColor());
    }
}
