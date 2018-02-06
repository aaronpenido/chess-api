package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;

public abstract class Piece {

    PieceColor color;

    Piece(PieceColor color) {
        this.color = color;
    }

    abstract void move();
    public PieceColor getColor() {
        return color;
    }
}
