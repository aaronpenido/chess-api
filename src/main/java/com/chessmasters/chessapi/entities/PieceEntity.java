package com.chessmasters.chessapi.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class PieceEntity {

    private String color;
    @Embedded
    private SquareEntity square;
    private String type;

    public PieceEntity() {
    }

    public PieceEntity(String color, SquareEntity square, String type) {
        this.color = color;
        this.square = square;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public SquareEntity getSquare() {
        return square;
    }

    public String getType() {
        return type;
    }
}
