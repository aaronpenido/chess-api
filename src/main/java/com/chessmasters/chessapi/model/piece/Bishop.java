package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    void move() {

    }

    @Override
    public List<Coordinate> getValidCoordinates(Coordinate actualCoordinate) {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s Bishop", getColor());
    }
}
