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
        final int INITIAL_NUMBER = 2;
        final int actualNumber = actualCoordinate.getNumber();

        if(actualNumber == MAX_NUMBER) {
            return null;
        }

        Coordinate firstCoordinate = new Coordinate(actualCoordinate.getLetter(), actualNumber + 1);
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(firstCoordinate);

        if(actualNumber == INITIAL_NUMBER) {
            Coordinate secondCoordinate = new Coordinate(actualCoordinate.getLetter(), actualNumber + 2);
            coordinates.add(secondCoordinate);
        }

        final int letterIndex = actualCoordinate.getLetter().ordinal();
        final Coordinate.LetterFile[] letterValues = Coordinate.LetterFile.values();

        if(letterIndex >= 1) {
            coordinates.add(new Coordinate(letterValues[letterIndex - 1], actualNumber + 1));
        }

        if(letterIndex < letterValues.length - 1) {
            coordinates.add(new Coordinate(letterValues[letterIndex + 1], actualNumber + 1));
        }

        return coordinates;
    }

    private List<Coordinate> getValidBlackCoordinates(Coordinate actualCoordinate) {
        final int MAX_NUMBER = 1;
        final int INITIAL_NUMBER = 7;
        final int actualNumber = actualCoordinate.getNumber();

        if(actualCoordinate.getNumber() == MAX_NUMBER) {
            return null;
        }

        Coordinate firstCoordinate = new Coordinate(actualCoordinate.getLetter(), actualNumber - 1);
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(firstCoordinate);

        if(actualNumber == INITIAL_NUMBER) {
            Coordinate secondCoordinate = new Coordinate(actualCoordinate.getLetter(), actualNumber - 2);
            coordinates.add(secondCoordinate);
        }

        final int letterIndex = actualCoordinate.getLetter().ordinal();
        final Coordinate.LetterFile[] letterValues = Coordinate.LetterFile.values();

        if(letterIndex >= 1) {
            coordinates.add(new Coordinate(letterValues[letterIndex - 1], actualNumber - 1));
        }

        if(letterIndex < letterValues.length - 1) {
            coordinates.add(new Coordinate(letterValues[letterIndex + 1], actualNumber - 1));
        }

        return coordinates;
    }

    @Override
    public String toString() {
        return String.format("%s Pawn", getColor());
    }
}
