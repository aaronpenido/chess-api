package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Letter;
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

    @Test
    public void kingHasValidMoves() {
        king = new King(WHITE, new Square(Letter.E, 1));

        assertThat(king.moves()).isNotNull();
        assertThat(king.moves()).isNotEmpty();
    }

    @Test
    public void kingMovesOneSquareAhead() {
        king = new King(WHITE, new Square(Letter.E, 1));
        assertThat(king.moves()).contains(new Square(Letter.E, 2));
    }

    @Test
    public void kingMovesOneSquareAheadInLeftDiagonal() {
        king = new King(WHITE, new Square(Letter.E, 1));
        assertThat(king.moves()).contains(new Square(Letter.D, 2));
    }

    @Test
    public void kingMovesOneSquareAheadInRightDiagonal() {
        king = new King(WHITE, new Square(Letter.E, 1));
        assertThat(king.moves()).contains(new Square(Letter.F, 2));
    }

    @Test
    public void kingMovesOneSquareBehind() {
        king = new King(WHITE, new Square(Letter.E, 2));
        assertThat(king.moves()).contains(new Square(Letter.E, 1));
    }

    @Test
    public void kingMovesOneSquareBehindInLeftDiagonal() {
        king = new King(WHITE, new Square(Letter.E, 2));
        assertThat(king.moves()).contains(new Square(Letter.D, 1));
    }

    @Test
    public void kingMovesOneSquareBehindInRightDiagonal() {
        king = new King(WHITE, new Square(Letter.E, 2));
        assertThat(king.moves()).contains(new Square(Letter.F, 1));
    }

    @Test
    public void kingMovesOneSquareLeft() {
        king = new King(WHITE, new Square(Letter.E, 1));
        assertThat(king.moves()).contains(new Square(Letter.D, 1));
    }

    @Test
    public void kingMovesOneSquareRight() {
        king = new King(WHITE, new Square(Letter.E, 1));
        assertThat(king.moves()).contains(new Square(Letter.F, 1));
    }

    @Test
    public void kingDoesNotMoveAheadWhenItsInTopBorder() {
        king = new King(WHITE, new Square(Letter.E, 8));
        assertThat(king.moves()).doesNotContain(new Square(Letter.E, 9));
    }

    @Test
    public void kingDoesNotMoveBehindWhenItsInDownBorder() {
        king = new King(WHITE, new Square(Letter.E, 1));
        assertThat(king.moves()).doesNotContain(new Square(Letter.E, 0));
    }

    @Test
    public void kingDoesNotMoveRightWhenItsInRightBorder() {
        king = new King(WHITE, new Square(Letter.H, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.H, 3));
        squares.add(new Square(Letter.H, 5));
        squares.add(new Square(Letter.G, 3));
        squares.add(new Square(Letter.G, 4));
        squares.add(new Square(Letter.G, 5));

        assertThat(king.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void kingDoesNotMoveLeftWhenItsInLeftBorder() {
        king = new King(WHITE, new Square(Letter.A, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 3));
        squares.add(new Square(Letter.A, 5));
        squares.add(new Square(Letter.B, 3));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.B, 5));

        assertThat(king.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}