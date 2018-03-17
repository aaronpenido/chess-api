package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.Square;
import com.chessmasters.chessapi.enums.Letter;

import java.util.Objects;

public class SquareModel {

    private int number;
    private Letter letter;

    public SquareModel() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquareModel that = (SquareModel) o;
        return number == that.number &&
                letter == that.letter;
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, letter);
    }
}
