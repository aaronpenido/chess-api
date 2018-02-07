package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;

import java.util.List;

public class Rook extends Piece {

    public Rook(PieceColor color) {
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
        return String.format("%s Rook", getColor());
    }
}
