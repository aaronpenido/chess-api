package com.chessmasters.chessapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Knight extends PieceModel {

    public Knight(@JsonProperty("square") SquareModel square, @JsonProperty("color") String color) {
        super(square, color);
    }
}
