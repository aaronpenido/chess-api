package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    void move() {

    }

    @Override
    public String toString() {
        return String.format("%s Rook", getColor());
    }
}
