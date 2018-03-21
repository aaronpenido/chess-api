package com.chessmasters.chessapi.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Piece {

    private String color;
    @Embedded
    private Square square;
    private String type;

    public Piece() {
    }

    public Piece(String color, Square square, String type) {
        this.color = color;
        this.square = square;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public Square getSquare() {
        return square;
    }

    public String getType() {
        return type;
    }
}
