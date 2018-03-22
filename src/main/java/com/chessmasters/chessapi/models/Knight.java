package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.Piece;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Knight extends PieceModel {

    public Knight(@JsonProperty("piece") Piece piece) {
        super(piece);
    }
}
