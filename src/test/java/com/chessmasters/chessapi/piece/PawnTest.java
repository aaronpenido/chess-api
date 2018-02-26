package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    private Pawn pawn;
    private Board board;
    
    @Test
    public void pawnHasValidMoves() {
        pawn = new Pawn(WHITE, new Square(Letter.A, 1));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).isNotEmpty();
    }

    @Test
    public void whiteMovesOneSquareAhead() {
        pawn = new Pawn(WHITE, new Square(Letter.E, 4));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(new Square(Letter.E, 5));
    }

    @Test
    public void blackMovesOneSquareAhead() {
        pawn = new Pawn(BLACK, new Square(Letter.E, 4));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(new Square(Letter.E, 3));
    }

    @Test
    public void whiteDoesNotMoveWhenItsInTopBorder() {
        pawn = new Pawn(WHITE, new Square(Letter.E, 8));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).isEmpty();
    }

    @Test
    public void blackDoesNotMoveWhenItsInTopBorder() {
        pawn = new Pawn(BLACK, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).isEmpty();
    }

    @Test
    public void whiteMovesTwoSquaresAheadOnlyWhenItsInSecondRow() {
        pawn = new Pawn(WHITE, new Square(Letter.E, 2));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).contains(new Square(Letter.E, 4));
    }

    @Test
    public void blackMovesTwoSquaresAheadOnlyWhenItsInSeventhRow() {
        pawn = new Pawn(BLACK, new Square(Letter.E, 7));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).contains(new Square(Letter.E, 5));
    }

    @Test
    public void whiteDoesNotMoveTwoSquaresAheadWhenItsNotInSecondRow() {
        pawn = new Pawn(WHITE, new Square(Letter.E, 3));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).doesNotContain(new Square(Letter.E, 5));
    }

    @Test
    public void blackDoesNotMoveTwoSquaresAheadWhenItsNotInSeventhRow() {
        pawn = new Pawn(BLACK, new Square(Letter.E, 6));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).doesNotContain(new Square(Letter.E, 4));
    }

    @Test
    public void whiteMovesToLeftDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        pawn = new Pawn(WHITE, new Square(Letter.E, 4));
        Square enemySquare = new Square(Letter.D, 5);
        Pawn blackPawn = new Pawn(BLACK, enemySquare);
        board = new Board(Arrays.asList(pawn, blackPawn));

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(
                new Square(Letter.E, 5),
                enemySquare);
    }

    @Test
    public void whiteMovesToRightDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        pawn = new Pawn(WHITE, new Square(Letter.E, 4));
        Square enemySquare = new Square(Letter.F, 5);
        Pawn blackPawn = new Pawn(BLACK, enemySquare);
        board = new Board(Arrays.asList(pawn, blackPawn));

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(
                new Square(Letter.E, 5),
                enemySquare);
    }

    @Test
    public void blackMovesToLeftDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        pawn = new Pawn(BLACK, new Square(Letter.E, 4));
        Square enemySquare = new Square(Letter.F, 3);
        Pawn enemyPawn = new Pawn(WHITE, enemySquare);
        board = new Board(Arrays.asList(pawn, enemyPawn));

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(
                new Square(Letter.E, 3),
                enemySquare);
    }

    @Test
    public void blackMovesToRightDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        pawn = new Pawn(BLACK, new Square(Letter.E, 4));
        Square enemySquare = new Square(Letter.D, 3);
        Pawn enemyPawn = new Pawn(WHITE, enemySquare);
        board = new Board(Arrays.asList(pawn, enemyPawn));

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(
                new Square(Letter.E, 3),
                enemySquare);
    }

    @Test
    public void whiteDoesNotMoveWhenThereIsAFriendlyPieceAhead() {
        pawn = new Pawn(WHITE, new Square(Letter.E, 4));
        Pawn friendlyPawn = new Pawn(WHITE, new Square(Letter.E, 5));
        board = new Board(Arrays.asList(pawn, friendlyPawn));

        assertThat(pawn.moves(board)).isEmpty();
    }

    @Test
    public void blackDoesNotMoveWhenThereIsAFriendlyPieceAhead() {
        pawn = new Pawn(BLACK, new Square(Letter.E, 4));
        Pawn friendlyPawn = new Pawn(WHITE, new Square(Letter.E, 3));
        board = new Board(Arrays.asList(pawn, friendlyPawn));

        assertThat(pawn.moves(board)).isEmpty();
    }

    @Test
    public void whiteDoesNotMoveTwoSquaresAheadAtInitialPositionIfThereIsAPieceInTheWay() {
        pawn = new Pawn(WHITE, new Square(Letter.E, 2));
        Pawn friendlyPawn = new Pawn(WHITE, new Square(Letter.E, 4));
        board = new Board(Arrays.asList(pawn, friendlyPawn));

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(new Square(Letter.E, 3));
    }

    @Test
    public void blackDoesNotMoveTwoSquaresAheadAtInitialPositionIfThereIsAPieceInTheWay() {
        pawn = new Pawn(BLACK, new Square(Letter.E, 7));
        Pawn friendlyPawn = new Pawn(WHITE, new Square(Letter.E, 5));
        board = new Board(Arrays.asList(pawn, friendlyPawn));

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(new Square(Letter.E, 6));
    }
}