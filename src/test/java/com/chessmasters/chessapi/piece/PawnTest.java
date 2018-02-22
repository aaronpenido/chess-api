package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Pawn;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PawnTest {

    @Test
    public void pawnHasValidMoves() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.A, 1));

        assertThat(pawn.moves()).isNotNull();
        assertThat(pawn.moves()).isNotEmpty();
    }

    @Test
    public void whiteMovesOneSquareAhead() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.E, 4));
        assertThat(pawn.moves()).contains(new Square(Letter.E, 5));
    }

    @Test
    public void blackMovesOneSquareAhead() {
        Pawn pawn = new Pawn(BLACK, new Square(Letter.E, 4));
        assertThat(pawn.moves()).contains(new Square(Letter.E, 3));
    }

    @Test
    public void whiteDoesNotMoveWhenItsInTopBorder() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.E, 8));
        assertThat(pawn.moves()).isEmpty();
    }

    @Test
    public void blackDoesNotMoveWhenItsInTopBorder() {
        Pawn pawn = new Pawn(BLACK, new Square(Letter.E, 1));
        assertThat(pawn.moves()).isEmpty();
    }

    @Test
    public void whiteMovesTwoSquaresAheadOnlyWhenItsInSecondRow() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.E, 2));
        assertThat(pawn.moves()).contains(new Square(Letter.E, 4));
    }

    @Test
    public void blackMovesTwoSquaresAheadOnlyWhenItsInSeventhRow() {
        Pawn pawn = new Pawn(BLACK, new Square(Letter.E, 7));
        assertThat(pawn.moves()).contains(new Square(Letter.E, 5));
    }

    @Test
    public void whiteDoesNotMoveTwoSquaresAheadWhenItsNotInSecondRow() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.E, 3));
        assertThat(pawn.moves()).doesNotContain(new Square(Letter.E, 5));
    }

    @Test
    public void blackDoesNotMoveTwoSquaresAheadWhenItsNotInSeventhRow() {
        Pawn pawn = new Pawn(BLACK, new Square(Letter.E, 6));
        assertThat(pawn.moves()).doesNotContain(new Square(Letter.E, 4));
    }

    @Test
    public void whiteMovesOneSquareInRightDiagonal() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.E, 3));
        assertThat(pawn.moves()).contains(new Square(Letter.F, 4));
    }

    @Test
    public void blackMovesOneSquareInRightDiagonal() {
        Pawn pawn = new Pawn(BLACK, new Square(Letter.E, 3));
        assertThat(pawn.moves()).contains(new Square(Letter.D, 2));
    }

    @Test
    public void whiteMovesOneSquareInLeftDiagonal() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.E, 3));
        assertThat(pawn.moves()).contains(new Square(Letter.D, 4));
    }

    @Test
    public void blackMovesOneSquareInLeftDiagonal() {
        Pawn pawn = new Pawn(BLACK, new Square(Letter.E, 3));
        assertThat(pawn.moves()).contains(new Square(Letter.F, 2));
    }

    @Test
    public void whiteDoesNotMoveRightDiagonalWhenItsInRightBorder() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.H, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.H, 5));
        squares.add(new Square(Letter.G, 5));

        assertThat(pawn.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void blackDoesNotMoveRightDiagonalWhenItsInRightBorder() {
        Pawn pawn = new Pawn(BLACK, new Square(Letter.A, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 3));
        squares.add(new Square(Letter.B, 3));

        assertThat(pawn.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void whiteDoesNotMoveLeftDiagonalWhenItsInLeftBorder() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.A, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 5));
        squares.add(new Square(Letter.B, 5));

        assertThat(pawn.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void blackDoesNotMoveLeftDiagonalWhenItsInLeftBorder() {
        Pawn pawn = new Pawn(BLACK, new Square(Letter.H, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.H, 3));
        squares.add(new Square(Letter.G, 3));

        assertThat(pawn.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}