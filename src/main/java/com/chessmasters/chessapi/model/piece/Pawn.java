package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;

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
        final int MAX_NUMBER = 8;
        if(actualCoordinate.getNumber() == MAX_NUMBER) {
            return null;
        }

        Coordinate firstCoordinate = new Coordinate(actualCoordinate.getLetter(), 3);
        Coordinate secondCoordinate = new Coordinate(actualCoordinate.getLetter(), 4);

        List<Coordinate> coordinates = new ArrayList<>();

        coordinates.add(firstCoordinate);
        coordinates.add(secondCoordinate);

        final int letterIndex = actualCoordinate.getLetter().ordinal();
        final Coordinate.LetterFile[] letterValues = Coordinate.LetterFile.values();

        if(letterIndex >= 1) {
            coordinates.add(new Coordinate(letterValues[letterIndex - 1], 3));
        }

        if(letterIndex < letterValues.length - 1) {
            coordinates.add(new Coordinate(letterValues[letterIndex + 1], 3));
        }

        return coordinates;
    }

    private List<Coordinate> getValidBlackCoordinates(Coordinate actualCoordinate) {
        final int MAX_NUMBER = 1;
        if(actualCoordinate.getNumber() == MAX_NUMBER) {
            return null;
        }

        Coordinate firstCoordinate = new Coordinate(actualCoordinate.getLetter(), 6);
        Coordinate secondCoordinate = new Coordinate(actualCoordinate.getLetter(), 5);

        List<Coordinate> coordinates = new ArrayList<>();

        coordinates.add(firstCoordinate);
        coordinates.add(secondCoordinate);

        final int letterIndex = actualCoordinate.getLetter().ordinal();
        final Coordinate.LetterFile[] letterValues = Coordinate.LetterFile.values();

        if(letterIndex >= 1) {
            coordinates.add(new Coordinate(letterValues[letterIndex - 1], 6));
        }

        if(letterIndex < letterValues.length - 1) {
            coordinates.add(new Coordinate(letterValues[letterIndex + 1], 6));
        }

        return coordinates;
    }

    @Override
    public String toString() {
        return String.format("%s Pawn", getColor());
    }
}
