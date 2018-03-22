package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PieceEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pawn extends Piece {

    @JsonCreator
    public Pawn(@JsonProperty("pieceEntity") PieceEntity pieceEntity) {
        super(pieceEntity);
    }
}
