package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PieceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Knight extends Piece {

    public Knight(@JsonProperty("pieceEntity") PieceEntity pieceEntity) {
        super(pieceEntity);
    }
}
