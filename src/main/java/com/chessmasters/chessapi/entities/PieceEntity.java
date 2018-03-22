package com.chessmasters.chessapi.entities;

import com.chessmasters.chessapi.enums.Color;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class PieceEntity {

    @Enumerated(EnumType.STRING)
    private Color color;
    @Embedded
    private SquareEntity square;
    private String type;

    public PieceEntity() {
    }

    public PieceEntity(Color color, SquareEntity square, String type) {
        this.color = color;
        this.square = square;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public SquareEntity getSquare() {
        return square;
    }

    public String getType() {
        return type;
    }
}
