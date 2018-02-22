package com.chessmasters.chessapi;

import java.util.Objects;

public class Square {
    private char letter;
    private int number;
    private Letter letter2;

    public Square(char letter, int number) {
        this.letter = letter;
        this.number = number;
        this.letter2 = Letter.valueOf(String.valueOf(letter));
    }

    public Square(Letter letter2, int number) {
        this.letter2 = letter2;
        this.number = number;
        this.letter = letter2.name().charAt(0);
    }

    public char getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    public Letter getLetter2() {
        return letter2;
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
