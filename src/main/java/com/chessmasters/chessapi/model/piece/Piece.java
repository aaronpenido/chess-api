package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private PieceColor color;

    Piece(PieceColor color) {
        this.color = color;
    }

    abstract void move();
    public abstract List<Coordinate> getValidCoordinates(Coordinate actualCoordinate);

    public PieceColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }


}
