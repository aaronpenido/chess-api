package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;

import static com.chessmasters.chessapi.model.Coordinate.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

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
        final int maxNumber = 8;
        final int initialNumber = 2;
        final int nextNumber = actualCoordinate.getNumber() + 1;
        final int nextTwoNumbers = actualCoordinate.getNumber() + 2;

        return getValidCoordinatesFromBothColors(actualCoordinate,
                maxNumber,
                initialNumber,
                nextNumber,
                nextTwoNumbers);
    }

    private List<Coordinate> getValidBlackCoordinates(Coordinate actualCoordinate) {
        final int maxNumber = 1;
        final int initialNumber = 7;
        final int nextNumber = actualCoordinate.getNumber() - 1;
        final int nextTwoNumbers = actualCoordinate.getNumber() - 2;

        return getValidCoordinatesFromBothColors(actualCoordinate,
                maxNumber,
                initialNumber,
                nextNumber,
                nextTwoNumbers);
    }

    private List<Coordinate> getValidCoordinatesFromBothColors(Coordinate actualCoordinate,
                                                               final int maxNumber,
                                                               final int initialNumber,
                                                               final int nextNumber,
                                                               final int nextTwoNumbers) {
        final int actualNumber = actualCoordinate.getNumber();
        final LetterFile actualLetter = actualCoordinate.getLetter();

        if(actualNumber == maxNumber) {
            return null;
        }

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(actualLetter, nextNumber));

        if(actualNumber == initialNumber) {
            coordinates.add(new Coordinate(actualLetter, nextTwoNumbers));
        }

        final int letterIndex = actualLetter.ordinal();
        final Coordinate.LetterFile[] letterValues = Coordinate.LetterFile.values();

        final boolean isLetterNotOnLeftBorder = letterIndex >= 1;
        if(isLetterNotOnLeftBorder) {
            coordinates.add(new Coordinate(letterValues[letterIndex - 1], nextNumber));
        }

        final boolean isLetterNotOnRightBorder = letterIndex < letterValues.length - 1;
        if(isLetterNotOnRightBorder) {
            coordinates.add(new Coordinate(letterValues[letterIndex + 1], nextNumber));
        }

        return coordinates;
    }

    @Override
    public String toString() {
        return String.format("%s Pawn", getColor());
    }
}
