package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.enums.Color;

public class GamePiece {

    private Color color;
    private Square square;

    public GamePiece() {
    }

    public GamePiece(Color color, Square square) {
        this.color = color;
        this.square = square;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }
}
