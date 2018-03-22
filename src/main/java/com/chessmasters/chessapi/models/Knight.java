package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PieceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Knight extends PieceModel {

    public Knight(@JsonProperty("piece") PieceEntity piece) {
        super(piece);
    }
}
