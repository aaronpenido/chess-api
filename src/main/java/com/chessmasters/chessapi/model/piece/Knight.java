package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    void move() {

    }

    @Override
    public String toString() {
        return String.format("%s Knight", getColor());
    }
}
