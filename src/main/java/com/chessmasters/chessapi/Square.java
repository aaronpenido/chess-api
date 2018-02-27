package com.chessmasters.chessapi;

import java.util.Objects;
import java.util.Optional;

public class Square {
    private int number;
    private Letter letter;

    public Square(Letter letter, int number) {
        this.letter = letter;
        this.number = number;
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

    public Optional<Square> nextLetter() {
        if(Letter.hasNextLetter(letter)) {
            return Optional.of(new Square(Letter.nextLetter(letter), number));
        }
        return Optional.empty();
    }

    public Optional<Square> previousLetter() {
        if(Letter.hasPreviousLetter(letter)) {
            return Optional.of(new Square(Letter.previousLetter(letter), number));
        }
        return Optional.empty();
    }

    public Optional<Square> nextNumber() {
        if(number < 8) {
            return Optional.of(new Square(letter, number + 1));
        }
        return Optional.empty();
    }

    public Optional<Square> previousNumber() {
        if(number > 1) {
            return Optional.of(new Square(letter, number - 1));
        }
        return Optional.empty();
    }

    public Optional<Square> nextNumberAndLetter() {
        Optional<Square> nextLetter = nextLetter();
        Optional<Square> nextNumber = nextNumber();

        if(nextLetter.isPresent() && nextNumber.isPresent()) {
            return Optional.of(new Square(
                    nextLetter.get().getLetter(),
                    nextNumber.get().getNumber()));
        }

        return Optional.empty();
    }

    public Optional<Square> nextNumberAndPreviousLetter() {
        Optional<Square> previousLetter = previousLetter();
        Optional<Square> nextNumber = nextNumber();

        if(previousLetter.isPresent() && nextNumber.isPresent()) {
            return Optional.of(new Square(
                    previousLetter.get().getLetter(),
                    nextNumber.get().getNumber()));
        }

        return Optional.empty();
    }

    public Optional<Square> previousNumberAndLetter() {
        Optional<Square> previousLetter = previousLetter();
        Optional<Square> previousNumber = previousNumber();

        if(previousLetter.isPresent() && previousNumber.isPresent()) {
            return Optional.of(new Square(
                    previousLetter.get().getLetter(),
                    previousNumber.get().getNumber()));
        }

        return Optional.empty();
    }

    public Optional<Square> previousNumberAndNextLetter() {
        Optional<Square> nextLetter = nextLetter();
        Optional<Square> previousNumber = previousNumber();

        if(nextLetter.isPresent() && previousNumber.isPresent()) {
            return Optional.of(new Square(
                    nextLetter.get().getLetter(),
                    previousNumber.get().getNumber()));
        }

        return Optional.empty();
    }
}
