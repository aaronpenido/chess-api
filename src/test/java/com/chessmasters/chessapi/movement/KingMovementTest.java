package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.entity.piece.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.chessmasters.chessapi.enums.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KingMovementTest {

    KingMovement movement;
    Board board;
    @Mock
    Game game;

    @Test
    public void pathOnCenterWithoutOtherPieces() {
        Square square = new Square(Letter.E, 4);
        when(game.getPieces()).thenReturn(Collections.singletonList(new King(WHITE, square)));
        board = new Board(game);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.D, 5));
        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.F, 5));
        squares.add(new Square(Letter.D, 4));
        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.D, 3));
        squares.add(new Square(Letter.E, 3));
        squares.add(new Square(Letter.F, 3));

        movement = new KingMovement(board, square);

        assertThat(movement.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathOnBottomBorderWithoutOtherPieces() {
        Square square = new Square(Letter.E, 1);
        when(game.getPieces()).thenReturn(Collections.singletonList(new King(WHITE, square)));
        board = new Board(game);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.D, 1));
        squares.add(new Square(Letter.F, 1));
        squares.add(new Square(Letter.D, 2));
        squares.add(new Square(Letter.E, 2));
        squares.add(new Square(Letter.F, 2));

        movement = new KingMovement(board, square);

        assertThat(movement.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathOnTopBorderWithoutOtherPieces() {
        Square square = new Square(Letter.E, 8);
        when(game.getPieces()).thenReturn(Collections.singletonList(new King(WHITE, square)));
        board = new Board(game);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.D, 8));
        squares.add(new Square(Letter.F, 8));
        squares.add(new Square(Letter.D, 7));
        squares.add(new Square(Letter.E, 7));
        squares.add(new Square(Letter.F, 7));

        movement = new KingMovement(board, square);

        assertThat(movement.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathOnLeftBorderWithoutOtherPieces() {
        Square square = new Square(Letter.A, 4);
        when(game.getPieces()).thenReturn(Collections.singletonList(new King(WHITE, square)));
        board = new Board(game);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.A, 3));
        squares.add(new Square(Letter.A, 5));
        squares.add(new Square(Letter.B, 5));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.B, 3));

        movement = new KingMovement(board, square);

        assertThat(movement.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathOnRightBorderWithoutOtherPieces() {
        Square square = new Square(Letter.H, 4);
        when(game.getPieces()).thenReturn(Collections.singletonList(new King(WHITE, square)));
        board = new Board(game);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.H, 3));
        squares.add(new Square(Letter.H, 5));
        squares.add(new Square(Letter.G, 5));
        squares.add(new Square(Letter.G, 4));
        squares.add(new Square(Letter.G, 3));

        movement = new KingMovement(board, square);

        assertThat(movement.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathDoesNotContainSquareFilledWithFriendlyPiece() {
        Square square = new Square(Letter.H, 4);
        Square pawnSquare = new Square(Letter.H, 4);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new King(WHITE, square),
                new Pawn(WHITE, pawnSquare)));

        board = new Board(game);
        movement = new KingMovement(board, square);

        assertThat(movement.path()).doesNotContain(pawnSquare);
    }

    @Test
    public void pathDoesNotContainSquareUnderAttackByEnemyBishop() {
        Square square = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 5);
        Square bishopSquare = new Square(Letter.C, 8);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new King(WHITE, square),
                new Bishop(BLACK, bishopSquare)));

        board = new Board(game);
        movement = new KingMovement(board, square);

        assertThat(movement.path()).doesNotContain(destination);
    }

    @Test
    public void pathDoesNotContainSquareUnderAttackByEnemyRook() {
        Square square = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 5);
        Square rookSquare = new Square(Letter.F, 1);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new King(WHITE, square),
                new Rook(BLACK, rookSquare)));

        board = new Board(game);
        movement = new KingMovement(board, square);

        assertThat(movement.path()).doesNotContain(destination);
    }

    @Test
    public void pathDoesNotContainSquareUnderAttackByEnemyKnight() {
        Square square = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 5);
        Square knightSquare = new Square(Letter.E, 3);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new King(WHITE, square),
                new Knight(BLACK, knightSquare)));

        board = new Board(game);
        movement = new KingMovement(board, square);

        assertThat(movement.path()).doesNotContain(destination);
    }

    @Test
    public void pathDoesNotContainSquaresUnderAttackByEnemyPawn() {
        Square square = new Square(Letter.E, 4);
        Square pawnSquare = new Square(Letter.E, 5);

        List<Square> attackedSquares = new ArrayList<>();
        attackedSquares.add(new Square(Letter.D, 4));
        attackedSquares.add(new Square(Letter.F, 4));

        when(game.getPieces()).thenReturn(Arrays.asList(
                new King(WHITE, square),
                new Pawn(BLACK, pawnSquare)));

        board = new Board(game);
        movement = new KingMovement(board, square);

        assertThat(movement.path()).doesNotContainAnyElementsOf(attackedSquares);
    }

    @Test
    public void pathDoesNotContainSquaresUnderAttackByEnemyQueen() {
        Square square = new Square(Letter.E, 4);
        Square queenSquare = new Square(Letter.D, 6);

        List<Square> attackedSquares = new ArrayList<>();
        attackedSquares.add(new Square(Letter.D, 3));
        attackedSquares.add(new Square(Letter.D, 4));
        attackedSquares.add(new Square(Letter.D, 5));
        attackedSquares.add(new Square(Letter.E, 5));
        attackedSquares.add(new Square(Letter.F, 4));

        when(game.getPieces()).thenReturn(Arrays.asList(
                new King(WHITE, square),
                new Queen(BLACK, queenSquare)));

        board = new Board(game);
        movement = new KingMovement(board, square);

        assertThat(movement.path()).doesNotContainAnyElementsOf(attackedSquares);
    }

    @Test
    public void pathDoesNotContainSquaresUnderAttackByEnemyKing() {
        Square square = new Square(Letter.E, 4);
        Square kingSquare = new Square(Letter.E, 6);

        List<Square> attackedSquares = new ArrayList<>();
        attackedSquares.add(new Square(Letter.D, 5));
        attackedSquares.add(new Square(Letter.E, 5));
        attackedSquares.add(new Square(Letter.F, 5));

        when(game.getPieces()).thenReturn(Arrays.asList(
                new King(WHITE, square),
                new King(BLACK, kingSquare)));

        board = new Board(game);
        movement = new KingMovement(board, square);

        assertThat(movement.path()).doesNotContainAnyElementsOf(attackedSquares);
    }
}