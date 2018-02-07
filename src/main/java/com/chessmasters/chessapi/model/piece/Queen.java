package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    void move() {

    }

    @Override
    public String toString() {
        return String.format("%s Queen", getColor());
    }
}
