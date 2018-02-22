package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Pawn;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PawnTest {

    private Pawn whitePawn;
    private Pawn blackPawn;
    private char letter;
    private int number;
    private int nextWhiteNumber;
    private int nextBlackNumber;
    private char previousLetter;
    private char nextLetter;

    @Before
    public void setUp() {
        letter = (char)(new Random().nextInt(5) + 66);
        number = new Random().nextInt(5) + 2;
        nextWhiteNumber = number + 1;
        nextBlackNumber = number - 1;
        previousLetter = (char)(letter - 1);
        nextLetter = (char)(letter + 1);

        whitePawn = new Pawn(WHITE, new Square(letter, number));
        blackPawn = new Pawn(BLACK, new Square(letter, number));
    }

    @Test
    public void pawnHasValidMoves() {
        assertThat(whitePawn.moves()).isNotNull();
        assertThat(whitePawn.moves()).isNotEmpty();
    }

    @Test
    public void whiteMovesOneSquareAhead() {
        assertThat(whitePawn.moves()).contains(new Square(letter, nextWhiteNumber));
    }

    @Test
    public void blackMovesOneSquareAhead() {
        assertThat(blackPawn.moves()).contains(new Square(letter, nextBlackNumber));
    }

    @Test
    public void whiteDoesNotMoveWhenItsInTopBorder() {
        whitePawn = new Pawn(WHITE, new Square(letter, 8));
        assertThat(whitePawn.moves()).doesNotContain(new Square(letter, 9));
    }

    @Test
    public void blackDoesNotMoveWhenItsInTopBorder() {
        blackPawn = new Pawn(BLACK, new Square(letter, 1));
        assertThat(blackPawn.moves()).doesNotContain(new Square(letter, 0));
    }

    @Test
    public void whiteMovesTwoSquaresAheadOnlyWhenItsInSecondRow() {
        whitePawn = new Pawn(WHITE, new Square(letter, 2));
        assertThat(whitePawn.moves()).contains(new Square(letter, 4));
    }

    @Test
    public void blackMovesTwoSquaresAheadOnlyWhenItsInSeventhRow() {
        blackPawn = new Pawn(BLACK, new Square(letter, 7));
        assertThat(blackPawn.moves()).contains(new Square(letter, 5));
    }

    @Test
    public void whiteDoesNotMoveTwoSquaresAheadWhenItsNotInSecondRow() {
        number = new Random().nextInt(4) + 3;
        whitePawn = new Pawn(WHITE, new Square(letter, number));
        assertThat(whitePawn.moves()).doesNotContain(new Square(letter, number + 2));
    }

    @Test
    public void blackDoesNotMoveTwoSquaresAheadWhenItsNotInSeventhRow() {
        number = new Random().nextInt(4) + 2;
        blackPawn = new Pawn(BLACK, new Square(letter, number));
        assertThat(blackPawn.moves()).doesNotContain(new Square(letter, number - 2));
    }

    @Test
    public void whiteMovesOneSquareInRightDiagonal() {
        assertThat(whitePawn.moves()).contains(new Square(nextLetter, nextWhiteNumber));
    }

    @Test
    public void blackMovesOneSquareInRightDiagonal() {
        assertThat(blackPawn.moves()).contains(new Square(nextLetter, nextBlackNumber));
    }

    @Test
    public void whiteMovesOneSquareInLeftDiagonal() {
        assertThat(whitePawn.moves()).contains(new Square(previousLetter, nextWhiteNumber));
    }

    @Test
    public void blackMovesOneSquareInLeftDiagonal() {
        assertThat(blackPawn.moves()).contains(new Square(previousLetter, nextBlackNumber));
    }

    @Test
    public void whiteDoesNotMoveRightDiagonalWhenItsInRightBorder() {
        whitePawn = new Pawn(WHITE, new Square('H', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.H, 5));
        squares.add(new Square(Letter.G, 5));

        assertThat(whitePawn.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void blackDoesNotMoveRightDiagonalWhenItsInRightBorder() {
        blackPawn = new Pawn(BLACK, new Square('A', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 3));
        squares.add(new Square(Letter.B, 3));

        assertThat(blackPawn.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void whiteDoesNotMoveLeftDiagonalWhenItsInLeftBorder() {
        whitePawn = new Pawn(WHITE, new Square('A', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 5));
        squares.add(new Square(Letter.B, 5));

        assertThat(whitePawn.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void blackDoesNotMoveLeftDiagonalWhenItsInLeftBorder() {
        blackPawn = new Pawn(BLACK, new Square('H', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.H, 3));
        squares.add(new Square(Letter.G, 3));

        assertThat(blackPawn.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}