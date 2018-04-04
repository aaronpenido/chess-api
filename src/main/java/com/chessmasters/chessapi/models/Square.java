package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.SquareEntity;
import com.chessmasters.chessapi.enums.Letter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Square {

    private int number;
    private Letter letter;

    public Square(@JsonProperty("squareEntity") SquareEntity squareEntity) {
        if(squareEntity != null) {
            this.number = squareEntity.getNumber();
            this.letter = squareEntity.getLetter();
        }
    }

    public int getNumber() {
        return number;
    }

    public Letter getLetter() {
        return letter;
    }

    @Override
    public String toString() {
        return String.format("%s%s", letter, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square that = (Square) o;
        return number == that.number &&
                letter == that.letter;
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, letter);
    }
}
