package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.Square;
import com.chessmasters.chessapi.enums.Letter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class SquareModel {

    private final int number;
    @Enumerated(EnumType.STRING)
    private final Letter letter;

    public SquareModel(Square square) {
        this.number = square.getNumber();
        this.letter = square.getLetter();
    }

    public int getNumber() {
        return number;
    }

    public Letter getLetter() {
        return letter;
    }
}
