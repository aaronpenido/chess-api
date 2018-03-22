package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PieceEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
        include = As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @Type(value = Pawn.class),
        @Type(value = Knight.class)
})
public abstract class PieceModel {

    private SquareModel square;
    private String color;
    private String type;

    public PieceModel() {
    }

    public PieceModel(PieceEntity piece) {
        if(piece != null) {
            this.square = new SquareModel(piece.getSquare());
            this.color = piece.getColor();
            this.type = piece.getType();
        }
    }

    public SquareModel getSquare() {
        return square;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
