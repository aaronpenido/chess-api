package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.SquareEntity;
import com.chessmasters.chessapi.enums.Letter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Square {

    private Letter letter;
    private int number;

    public Square(@JsonProperty("squareEntity") SquareEntity squareEntity) {
        if(squareEntity != null) {
            this.letter = squareEntity.getLetter();
            this.number = squareEntity.getNumber();
        }
    }

    public Square(Letter letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public Letter getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
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
