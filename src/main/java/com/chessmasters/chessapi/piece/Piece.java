package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected Color color;
    protected Square square;
    protected List<Square> moves;

    public Piece(Color color, Square square) {
        this.color = color;
        this.square = square;
        this.moves = new ArrayList<>();
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
