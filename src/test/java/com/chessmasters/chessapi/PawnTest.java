package com.chessmasters.chessapi;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PawnTest {

    private Pawn whitePawn;
    private Pawn blackPawn;
    private char letter;
    private int number;

    @Before
    public void setUp() {
        letter = (char)(new Random().nextInt(5) + 66);
        number = new Random().nextInt(5) + 2;
        whitePawn = new Pawn(WHITE, new Square(letter, number));
        blackPawn = new Pawn(BLACK, new Square(letter, number));
    }

    @Test
    public void pawnHasValidMoves() {
        assertThat(whitePawn.getMoves()).isNotNull();
        assertThat(whitePawn.getMoves()).isNotEmpty();
    }

    @Test
    public void whiteMovesOneSquareAhead() {
        assertThat(whitePawn.getMoves()).contains(new Square(letter, number + 1));
    }

    @Test
    public void blackMovesOneSquareAhead() {
        assertThat(blackPawn.getMoves()).contains(new Square(letter, number - 1));
    }

    @Test
    public void whiteDoesNotMoveWhenItsInTopBorder() {
        whitePawn = new Pawn(WHITE, new Square(letter, 8));
        assertThat(whitePawn.getMoves()).doesNotContain(new Square(letter, 9));
    }

    @Test
    public void blackDoesNotMoveWhenItsInTopBorder() {
        blackPawn = new Pawn(BLACK, new Square(letter, 1));
        assertThat(blackPawn.getMoves()).doesNotContain(new Square(letter, 0));
    }

    @Test
    public void whiteMovesTwoSquaresAheadOnlyWhenItsInSecondRow() {
        whitePawn = new Pawn(WHITE, new Square(letter, 2));
        assertThat(whitePawn.getMoves()).contains(new Square(letter, 4));
    }

    @Test
    public void blackMovesTwoSquaresAheadOnlyWhenItsInSeventhRow() {
        blackPawn = new Pawn(BLACK, new Square(letter, 7));
        assertThat(blackPawn.getMoves()).contains(new Square(letter, 5));
    }

    @Test
    public void whiteDoesNotMoveTwoSquaresAheadWhenItsNotInSecondRow() {
        number = new Random().nextInt(4) + 3;
        whitePawn = new Pawn(WHITE, new Square(letter, number));
        assertThat(whitePawn.getMoves()).doesNotContain(new Square(letter, number + 2));
    }

    @Test
    public void blackDoesNotMoveTwoSquaresAheadWhenItsNotInSeventhRow() {
        number = new Random().nextInt(4) + 2;
        blackPawn = new Pawn(BLACK, new Square(letter, number));
        assertThat(blackPawn.getMoves()).doesNotContain(new Square(letter, number - 2));
    }

    @Test
    public void whiteMovesOneSquareInRightDiagonal() {
        final char nextLetter = (char)(letter + 1);
        assertThat(whitePawn.getMoves()).contains(new Square(nextLetter, number + 1));
    }

    @Test
    public void blackMovesOneSquareInRightDiagonal() {
        final char nextLetter = (char)(letter + 1);
        assertThat(blackPawn.getMoves()).contains(new Square(nextLetter, number - 1));
    }

    @Test
    public void whiteMovesOneSquareInLeftDiagonal() {
        final char previousLetter = (char)(letter - 1);
        assertThat(whitePawn.getMoves()).contains(new Square(previousLetter, number + 1));
    }

    @Test
    public void blackMovesOneSquareInLeftDiagonal() {
        final char previousLetter = (char)(letter - 1);
        assertThat(blackPawn.getMoves()).contains(new Square(previousLetter, number - 1));
    }

    @Test
    public void whiteDoesNotMoveRightDiagonalWhenItsInRightBorder() {
        whitePawn = new Pawn(WHITE, new Square('H', number));
        assertThat(whitePawn.getMoves()).doesNotContain(new Square('I', number + 1));
    }

    @Test
    public void blackDoesNotMoveRightDiagonalWhenItsInRightBorder() {
        blackPawn = new Pawn(BLACK, new Square('H', number));
        assertThat(blackPawn.getMoves()).doesNotContain(new Square('I', number - 1));
    }

    @Test
    public void whiteDoesNotMoveLeftDiagonalWhenItsInLeftBorder() {
        whitePawn = new Pawn(WHITE, new Square('A', number));
        assertThat(whitePawn.getMoves()).doesNotContain(new Square('@', number + 1));
    }

    @Test
    public void blackDoesNotMoveLeftDiagonalWhenItsInLeftBorder() {
        blackPawn = new Pawn(BLACK, new Square('A', number));
        assertThat(blackPawn.getMoves()).doesNotContain(new Square('@', number - 1));
    }
}