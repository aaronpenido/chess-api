package com.chessmasters.chessapi;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected Color color;
    protected Square square;

    public Piece(Color color, Square square) {
        this.color = color;
        this.square = square;
    }

    public Color getColor() {
        return color;
    }

    public Square getSquare() {
        return square;
    }

    public abstract List<Square> moves();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(square, piece.square);
    }

    @Override
    public int hashCode() {

        return Objects.hash(square);
    }
}
