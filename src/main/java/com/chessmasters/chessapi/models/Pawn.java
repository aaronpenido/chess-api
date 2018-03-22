package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PieceEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pawn extends PieceModel {

    @JsonCreator
    public Pawn(@JsonProperty("piece") PieceEntity piece) {
        super(piece);
    }
}
