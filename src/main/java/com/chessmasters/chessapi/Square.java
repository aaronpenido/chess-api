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

    public Optional<Square> twoNextNumberAndOneNextLetter() {
        Optional<Square> nextNumberAndLetter = nextNumberAndLetter();

        if(nextNumberAndLetter.isPresent()) {
            Optional<Square> twoNextNumber = nextNumberAndLetter.get().nextNumber();

            if(twoNextNumber.isPresent()) {
                return Optional.of(new Square(
                        twoNextNumber.get().getLetter(),
                        twoNextNumber.get().getNumber()));
            }
        }

        return Optional.empty();
    }

    public Optional<Square> twoNextNumberAndOnePreviousLetter() {
        Optional<Square> nextNumberAndPreviousLetter = nextNumberAndPreviousLetter();

        if(nextNumberAndPreviousLetter.isPresent()) {
            Optional<Square> twoNextNumber = nextNumberAndPreviousLetter.get().nextNumber();

            if(twoNextNumber.isPresent()) {
                return Optional.of(new Square(
                        twoNextNumber.get().getLetter(),
                        twoNextNumber.get().getNumber()));
            }
        }
        return Optional.empty();
    }

    public Optional<Square> twoNextLetterAndOneNextNumber() {
        Optional<Square> nextNumberAndLetter = nextNumberAndLetter();

        if(nextNumberAndLetter.isPresent()) {
            Optional<Square> twoNextLetter = nextNumberAndLetter.get().nextLetter();

            if(twoNextLetter.isPresent()) {
                return Optional.of(new Square(
                        twoNextLetter.get().getLetter(),
                        twoNextLetter.get().getNumber()));
            }
        }
        return Optional.empty();
    }

    public Optional<Square> twoPreviousLetterAndOneNextNumber() {
        Optional<Square> nextNumberAndPreviousLetter = nextNumberAndPreviousLetter();

        if(nextNumberAndPreviousLetter.isPresent()) {
            Optional<Square> twoPreviousLetter = nextNumberAndPreviousLetter.get().previousLetter();

            if(twoPreviousLetter.isPresent()) {
                return Optional.of(new Square(
                        twoPreviousLetter.get().getLetter(),
                        twoPreviousLetter.get().getNumber()));
            }
        }
        return Optional.empty();
    }

    public Optional<Square> twoPreviousNumberAndOneNextLetter() {
        Optional<Square> previousNumberAndNextLetter = previousNumberAndNextLetter();

        if(previousNumberAndNextLetter.isPresent()) {
            Optional<Square> twoPreviousNumber = previousNumberAndNextLetter.get().previousNumber();

            if(twoPreviousNumber.isPresent()) {
                return Optional.of(new Square(
                        twoPreviousNumber.get().getLetter(),
                        twoPreviousNumber.get().getNumber()));
            }
        }

        return Optional.empty();
    }

    public Optional<Square> twoPreviousNumberAndOnePreviousLetter() {
        Optional<Square> previousNumberAndLetter = previousNumberAndLetter();

        if(previousNumberAndLetter.isPresent()) {
            Optional<Square> twoPreviousNumber = previousNumberAndLetter.get().previousNumber();

            if(twoPreviousNumber.isPresent()) {
                return Optional.of(new Square(
                        twoPreviousNumber.get().getLetter(),
                        twoPreviousNumber.get().getNumber()));
            }
        }
        return Optional.empty();
    }

    public Optional<Square> twoPreviousLetterAndOnePreviousNumber() {
        Optional<Square> previousNumberAndLetter = previousNumberAndLetter();

        if(previousNumberAndLetter.isPresent()) {
            Optional<Square> previousLetter = previousNumberAndLetter.get().previousLetter();

            if(previousLetter.isPresent()) {
                return Optional.of(new Square(
                        previousLetter.get().getLetter(),
                        previousLetter.get().getNumber()));
            }
        }
        return Optional.empty();
    }

    public Optional<Square> twoNextLetterAndOnePreviousNumber() {
        Optional<Square> previousNumberAndNextLetter = previousNumberAndNextLetter();

        if(previousNumberAndNextLetter.isPresent()) {
            Optional<Square> nextLetter = previousNumberAndNextLetter.get().nextLetter();

            if(nextLetter.isPresent()) {
                return Optional.of(new Square(
                        nextLetter.get().getLetter(),
                        nextLetter.get().getNumber()));
            }
        }
        return Optional.empty();
    }

}
