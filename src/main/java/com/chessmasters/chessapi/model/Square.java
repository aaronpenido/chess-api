package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.model.piece.Piece;

public class Square {
    private Piece piece;
    private Coordinate coordinate;

    public Square(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Piece getPiece() {
        return piece;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void fill(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Square{" +
                "piece=" + piece +
                ", coordinate=" + coordinate +
                '}';
    }
}
