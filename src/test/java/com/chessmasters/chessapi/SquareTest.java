package com.chessmasters.chessapi;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareTest {

    @Test
    public void nextLetter() {
        Square square = new Square(Letter.E, 4);
        Optional<Square> expected = Optional.of(new Square(Letter.F, 4));

        assertThat(square.nextLetter()).isEqualTo(expected);
    }

    @Test
    public void previousLetter() {
        Square square = new Square(Letter.E, 4);
        Optional<Square> expected = Optional.of(new Square(Letter.D, 4));

        assertThat(square.previousLetter()).isEqualTo(expected);
    }

    @Test
    public void nextNumber() {
        Square square = new Square(Letter.E, 4);
        Optional<Square> expected = Optional.of(new Square(Letter.E, 5));

        assertThat(square.nextNumber()).isEqualTo(expected);
    }

    @Test
    public void previousNumber() {
        Square square = new Square(Letter.E, 4);
        Optional<Square> expected = Optional.of(new Square(Letter.E, 3));

        assertThat(square.previousNumber()).isEqualTo(expected);
    }

    @Test
    public void nextNumberAndLetter() {
        Square square = new Square(Letter.E, 4);
        Optional<Square> expected = Optional.of(new Square(Letter.F, 5));

        assertThat(square.nextNumberAndLetter()).isEqualTo(expected);
    }

    @Test
    public void nextNumberAndPreviousLetter() {
        Square square = new Square(Letter.E, 4);
        Optional<Square> expected = Optional.of(new Square(Letter.D, 5));

        assertThat(square.nextNumberAndPreviousLetter()).isEqualTo(expected);
    }

    @Test
    public void previousNumberAndLetter() {
        Square square = new Square(Letter.E, 4);
        Optional<Square> expected = Optional.of(new Square(Letter.D, 3));

        assertThat(square.previousNumberAndLetter()).isEqualTo(expected);
    }

    @Test
    public void previousNumberAndNextLetter() {
        Square square = new Square(Letter.E, 4);
        Optional<Square> expected = Optional.of(new Square(Letter.F, 3));

        assertThat(square.previousNumberAndNextLetter()).isEqualTo(expected);
    }

    @Test
    public void bottomBorderDoesNotHavePreviousNumber() {
        Square square = new Square(Letter.E, 1);

        assertThat(square.previousNumber()).isEqualTo(Optional.empty());
        assertThat(square.previousNumberAndLetter()).isEqualTo(Optional.empty());
        assertThat(square.previousNumberAndNextLetter()).isEqualTo(Optional.empty());
    }

    @Test
    public void topBorderDoesNotHaveNextNumber() {
        Square square = new Square(Letter.E, 8);

        assertThat(square.nextNumber()).isEqualTo(Optional.empty());
        assertThat(square.nextNumberAndLetter()).isEqualTo(Optional.empty());
        assertThat(square.nextNumberAndPreviousLetter()).isEqualTo(Optional.empty());
    }

    @Test
    public void leftBorderDoesNotHavePreviousLetter() {
        Square square = new Square(Letter.A, 4);

        assertThat(square.previousLetter()).isEqualTo(Optional.empty());
        assertThat(square.previousNumberAndLetter()).isEqualTo(Optional.empty());
        assertThat(square.nextNumberAndPreviousLetter()).isEqualTo(Optional.empty());
    }

    @Test
    public void rightBorderDoesNotHaveNextLetter() {
        Square square = new Square(Letter.H, 4);

        assertThat(square.nextLetter()).isEqualTo(Optional.empty());
        assertThat(square.nextNumberAndLetter()).isEqualTo(Optional.empty());
        assertThat(square.previousNumberAndNextLetter()).isEqualTo(Optional.empty());
    }
}