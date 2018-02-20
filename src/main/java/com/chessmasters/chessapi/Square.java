package com.chessmasters.chessapi;

import java.util.Objects;

public class Square {
    private char letter;
    private int number;

    public Square(char letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public char getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return letter == square.letter &&
                number == square.number;
    }

    @Override
    public int hashCode() {

        return Objects.hash(letter, number);
    }

    @Override
    public String toString() {
        return String.valueOf(letter) + number;
    }
}
