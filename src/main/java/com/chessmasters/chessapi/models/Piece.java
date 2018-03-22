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
public abstract class Piece {

    private Square square;
    private String color;
    private String type;

    public Piece(PieceEntity pieceEntity) {
        if(pieceEntity != null) {
            this.square = new Square(pieceEntity.getSquare());
            this.color = pieceEntity.getColor();
            this.type = pieceEntity.getType();
        }
    }

    public Square getSquare() {
        return square;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
