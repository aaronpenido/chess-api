package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    Color color;
    Square square;

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

    public void setSquare(Square square) {
        this.square = square;
    }

    public abstract List<Square> moves();
    public abstract List<Square> moves(Board board);

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
