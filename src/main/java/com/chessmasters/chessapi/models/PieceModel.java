package com.chessmasters.chessapi.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = Id.NAME,
        include = As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = Pawn.class)
})
public abstract class PieceModel {

    private SquareModel square;
    private String color;

    public PieceModel() {
    }

    public PieceModel(SquareModel square, String color) {
        this.square = square;
        this.color = color;
    }

    public SquareModel getSquare() {
        return square;
    }

    public String getColor() {
        return color;
    }
}
