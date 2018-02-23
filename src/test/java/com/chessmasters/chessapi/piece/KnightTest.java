package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    public void knightHasValidMoves() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 1));

        assertThat(knight.moves()).isNotNull();
        assertThat(knight.moves()).isNotEmpty();
    }

    @Test
    public void knightMovesLeftAhead() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.D, 6));
        squares.add(new Square(Letter.C, 5));

        assertThat(knight.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightMovesLeftBehind() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.D, 2));

        assertThat(knight.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightMovesRightAhead() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.F, 6));
        squares.add(new Square(Letter.G, 5));

        assertThat(knight.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightMovesRightBehind() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.F, 2));
        squares.add(new Square(Letter.G, 3));

        assertThat(knight.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightMovesMovesToAllDirectionsInCenter() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.D, 6));
        squares.add(new Square(Letter.C, 5));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.D, 2));
        squares.add(new Square(Letter.F, 6));
        squares.add(new Square(Letter.G, 5));
        squares.add(new Square(Letter.F, 2));
        squares.add(new Square(Letter.G, 3));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightMovesOnlyRightWhenItsOnLeftBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.B, 6));
        squares.add(new Square(Letter.C, 5));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.B, 2));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightMovesOnlyLeftWhenItsOnRightBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.H, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.G, 6));
        squares.add(new Square(Letter.F, 5));
        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesOnLeftWhenItsOneSquareNearLeftBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.B, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 6));
        squares.add(new Square(Letter.A, 2));

        assertThat(knight.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesOnRightWhenItsOneSquareNearRightBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.G, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.H, 6));
        squares.add(new Square(Letter.H, 2));

        assertThat(knight.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyMoveAheadWhenItsOnBottomBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.D, 3));
        squares.add(new Square(Letter.C, 2));
        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyMoveBehindWhenItsOnTopBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.C, 7));
        squares.add(new Square(Letter.D, 6));
        squares.add(new Square(Letter.F, 6));
        squares.add(new Square(Letter.G, 7));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesBehindWhenItsOneSquareNearBottomBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 2));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.C, 1));
        squares.add(new Square(Letter.G, 1));
        squares.add(new Square(Letter.D, 4));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.G, 3));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesAheadWhenItsOneSquareNearTopBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 7));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.C, 8));
        squares.add(new Square(Letter.G, 8));
        squares.add(new Square(Letter.D, 5));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.F, 5));
        squares.add(new Square(Letter.G, 6));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesWhenItsOnLeftBottomBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.B, 3));
        squares.add(new Square(Letter.C, 2));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesWhenItsOnRightBottomBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.H, 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.G, 3));
        squares.add(new Square(Letter.F, 2));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesWhenItsOnLeftTopBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.B, 6));
        squares.add(new Square(Letter.C, 7));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesWhenItsOnRightTopBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.H, 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.G, 6));
        squares.add(new Square(Letter.F, 7));

        assertThat(knight.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}