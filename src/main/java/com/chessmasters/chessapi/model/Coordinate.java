package com.chessmasters.chessapi.model;

import java.util.Objects;

public class Coordinate {

    private LetterFile letter;
    private int number;

    public Coordinate(LetterFile letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public LetterFile getLetter() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return number == that.number &&
                Objects.equals(letter, that.letter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(letter, number);
    }

    public static enum LetterFile {
        A, B, C, D, E, F, G, H;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
}
