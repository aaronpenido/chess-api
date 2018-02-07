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

    @Override
    public String toString() {
        return String.format("%s Pawn", getColor());
    }
}
