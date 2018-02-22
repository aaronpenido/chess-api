package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.King;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class KingTest {

    private King king;
    private char letter;
    private int number;
    private char previousLetter;
    private char nextLetter;
    private int nextNumber;
    private int previousNumber;

    @Before
    public void setUp() {
        letter = (char)(new Random().nextInt(5) + 66);
        number = new Random().nextInt(5) + 2;
        previousLetter = (char)(letter - 1);
        nextLetter = (char)(letter + 1);
        previousNumber = number - 1;
        nextNumber = number + 1;

        king = new King(WHITE, new Square(letter, number));
    }

    @Test
    public void kingHasValidMoves() {
        assertThat(king.moves()).isNotNull();
        assertThat(king.moves()).isNotEmpty();
    }

    @Test
    public void kingMovesOneSquareAhead() {
        assertThat(king.moves()).contains(new Square(letter, nextNumber));
    }

    @Test
    public void kingMovesOneSquareAheadInLeftDiagonal() {
        assertThat(king.moves()).contains(new Square(previousLetter, nextNumber));
    }

    @Test
    public void kingMovesOneSquareAheadInRightDiagonal() {
        assertThat(king.moves()).contains(new Square(nextLetter, nextNumber));
    }

    @Test
    public void kingMovesOneSquareBehind() {
        assertThat(king.moves()).contains(new Square(letter, previousNumber));
    }

    @Test
    public void kingMovesOneSquareBehindInLeftDiagonal() {
        assertThat(king.moves()).contains(new Square(previousLetter, previousNumber));
    }

    @Test
    public void kingMovesOneSquareBehindInRightDiagonal() {
        assertThat(king.moves()).contains(new Square(nextLetter, previousNumber));
    }

    @Test
    public void kingMovesOneSquareLeft() {
        assertThat(king.moves()).contains(new Square(previousLetter, number));
    }

    @Test
    public void kingMovesOneSquareRight() {
        assertThat(king.moves()).contains(new Square(nextLetter, number));
    }

    @Test
    public void kingDoesNotMoveAheadWhenItsInTopBorder() {
        king = new King(WHITE, new Square(letter, 8));
        assertThat(king.moves()).doesNotContain(new Square(letter, 9));
    }

    @Test
    public void kingDoesNotMoveBehindWhenItsInDownBorder() {
        king = new King(WHITE, new Square(letter, 1));
        assertThat(king.moves()).doesNotContain(new Square(letter, 0));
    }

    @Test
    public void kingDoesNotMoveRightWhenItsInRightBorder() {
        king = new King(WHITE, new Square('H', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('H', 3));
        squares.add(new Square('H', 5));
        squares.add(new Square('G', 3));
        squares.add(new Square('G', 4));
        squares.add(new Square('G', 5));

        assertThat(king.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void kingDoesNotMoveLeftWhenItsInLeftBorder() {
        king = new King(WHITE, new Square('A', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 3));
        squares.add(new Square('A', 5));
        squares.add(new Square('B', 3));
        squares.add(new Square('B', 4));
        squares.add(new Square('B', 5));

        assertThat(king.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}