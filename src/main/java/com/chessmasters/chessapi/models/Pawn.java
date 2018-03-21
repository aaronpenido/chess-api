package com.chessmasters.chessapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pawn extends PieceModel {

    @JsonCreator
    public Pawn(@JsonProperty("square") SquareModel square, @JsonProperty("color") String color) {
        super(square, color);
    }
}
