package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;

public class King extends Piece {

    public King(PieceColor color) {
        super(color);
    }

    @Override
    void move() {

    }

    @Override
    public String toString() {
        return String.format("%s King", getColor());
    }
}
