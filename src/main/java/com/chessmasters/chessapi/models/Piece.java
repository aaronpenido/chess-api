package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PieceEntity;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.PieceType;

public class Piece {

    private Long id;
    private Square square;
    private Color color;
    private PieceType type;

    public Piece(PieceEntity pieceEntity) {
        if(pieceEntity != null) {
            this.id = pieceEntity.getId();
            this.square = new Square(pieceEntity.getSquare());
            this.color = pieceEntity.getColor();
            this.type = pieceEntity.getType();
        }
    }

    public Long getId() {
        return id;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }
}
