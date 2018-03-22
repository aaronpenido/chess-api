package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PieceEntity;
import com.chessmasters.chessapi.enums.Color;
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
    private Color color;
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

    public Color getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
