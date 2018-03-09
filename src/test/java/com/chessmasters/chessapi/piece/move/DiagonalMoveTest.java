package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Bishop;
import com.chessmasters.chessapi.piece.Rook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static com.chessmasters.chessapi.enums.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiagonalMoveTest {

    private DiagonalMove move;
    private Board board;
    @Mock
    private Game game;

    @Test
    public void movesAheadToLeftDiagonal() {
        Square square = new Square(Letter.D, 5);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.A, 8));
        squares.add(new Square(Letter.B, 7));
        squares.add(new Square(Letter.C, 6));

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadToRightDiagonal() {
        Square square = new Square(Letter.D, 5);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.E, 6));
        squares.add(new Square(Letter.F, 7));
        squares.add(new Square(Letter.G, 8));

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindToLeftDiagonal() {
        Square square = new Square(Letter.D, 5);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.A, 2));
        squares.add(new Square(Letter.B, 3));
        squares.add(new Square(Letter.C, 4));

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindToRightDiagonal() {
        Square square = new Square(Letter.D, 5);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.E, 4));
        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));
        squares.add(new Square(Letter.H, 1));

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadOnlyToRightDiagonalWhenItsInLeftBottomBorder() {
        Square square = new Square(Letter.A, 1);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.B, 2));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.D, 4));
        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.F, 6));
        squares.add(new Square(Letter.G, 7));
        squares.add(new Square(Letter.H, 8));

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadOnlyToLeftDiagonalWhenItsInRightBottomBorder() {
        Square square = new Square(Letter.H, 1);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.A, 8));
        squares.add(new Square(Letter.B, 7));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.D, 5));
        squares.add(new Square(Letter.E, 4));
        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindOnlyToRightDiagonalWhenItsInLeftTopBorder() {
        Square square = new Square(Letter.A, 8);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.B, 7));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.D, 5));
        squares.add(new Square(Letter.E, 4));
        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));
        squares.add(new Square(Letter.H, 1));

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindOnlyToLeftDiagonalWhenItsInRightTopBorder() {
        Square square = new Square(Letter.H, 8);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.A, 1));
        squares.add(new Square(Letter.B, 2));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.D, 4));
        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.F, 6));
        squares.add(new Square(Letter.G, 7));

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesOnlyAheadWhenItsInBottomBorder() {
        Square square = new Square(Letter.E, 1);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.A, 5));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.D, 2));
        squares.add(new Square(Letter.F, 2));
        squares.add(new Square(Letter.G, 3));
        squares.add(new Square(Letter.H, 4));

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesOnlyBehindWhenItsInTopBorder() {
        Square square = new Square(Letter.E, 8);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.A, 4));
        squares.add(new Square(Letter.B, 5));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.D, 7));
        squares.add(new Square(Letter.F, 7));
        squares.add(new Square(Letter.G, 6));
        squares.add(new Square(Letter.H, 5));

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesToAllDirectionsWhenItsInCenter() {
        Square square = new Square(Letter.E, 4);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Bishop(WHITE, square)));
        board = new Board(game);
        move = new DiagonalMove(board, square);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.B, 1));
        squares.add(new Square(Letter.C, 2));
        squares.add(new Square(Letter.D, 3));
        squares.add(new Square(Letter.F, 5));
        squares.add(new Square(Letter.G, 6));
        squares.add(new Square(Letter.H, 7));

        squares.add(new Square(Letter.A, 8));
        squares.add(new Square(Letter.B, 7));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.D, 5));

        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));
        squares.add(new Square(Letter.H, 1));

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void leftPathAheadEndsWhereThereIsAFriendlyPieceOnTheWay() {
        Square square = new Square(Letter.H, 4);
        Square friendlyPieceSquare = new Square(Letter.E, 7);
        Square nextSquareAfterFriendlyPiece = new Square(Letter.D, 8);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Bishop(WHITE, square),
                new Bishop(WHITE, friendlyPieceSquare)));

        board = new Board(game);
        move = new DiagonalMove(board, square);

        assertThat(move.path()).doesNotContain(friendlyPieceSquare);
        assertThat(move.path()).doesNotContain(nextSquareAfterFriendlyPiece);
    }

    @Test
    public void leftPathAheadEndsOnSquareFilledWithEnemyPiece() {
        Square square = new Square(Letter.H, 4);
        Square enemyPieceSquare = new Square(Letter.E, 7);
        Square nextSquareAfterEnemyPiece = new Square(Letter.D, 8);

        List<Square> squares = new ArrayList<>();
        squares.add(enemyPieceSquare);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(BLACK, enemyPieceSquare)));

        board = new Board(game);
        move = new DiagonalMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
        assertThat(move.path()).doesNotContain(nextSquareAfterEnemyPiece);
    }

    @Test
    public void leftPathBehindEndsWhereThereIsAFriendlyPieceOnTheWay() {
        Square square = new Square(Letter.H, 7);
        Square friendlyPieceSquare = new Square(Letter.D, 3);
        Square nextSquareAfterFriendlyPiece = new Square(Letter.C, 2);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Bishop(WHITE, square),
                new Bishop(WHITE, friendlyPieceSquare)));

        board = new Board(game);
        move = new DiagonalMove(board, square);

        assertThat(move.path()).doesNotContain(friendlyPieceSquare);
        assertThat(move.path()).doesNotContain(nextSquareAfterFriendlyPiece);
    }

    @Test
    public void leftPathBehindEndsOnSquareFilledWithEnemyPiece() {
        Square square = new Square(Letter.H, 7);
        Square enemyPieceSquare = new Square(Letter.D, 3);
        Square nextSquareAfterEnemyPiece = new Square(Letter.C, 2);

        List<Square> squares = new ArrayList<>();
        squares.add(enemyPieceSquare);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(BLACK, enemyPieceSquare)));

        board = new Board(game);
        move = new DiagonalMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
        assertThat(move.path()).doesNotContain(nextSquareAfterEnemyPiece);
    }

    @Test
    public void rightPathAheadEndsWhereThereIsAFriendlyPieceOnTheWay() {
        Square square = new Square(Letter.A, 4);
        Square friendlyPieceSquare = new Square(Letter.D, 7);
        Square nextSquareAfterFriendlyPiece = new Square(Letter.E, 8);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(WHITE, friendlyPieceSquare)));

        board = new Board(game);
        move = new DiagonalMove(board, square);

        assertThat(move.path()).doesNotContain(friendlyPieceSquare);
        assertThat(move.path()).doesNotContain(nextSquareAfterFriendlyPiece);
    }

    @Test
    public void rightPathAheadEndsOnSquareFilledWithEnemyPiece() {
        Square square = new Square(Letter.A, 4);
        Square enemyPieceSquare = new Square(Letter.D, 7);
        Square nextSquareAfterEnemyPiece = new Square(Letter.E, 8);

        List<Square> squares = new ArrayList<>();
        squares.add(enemyPieceSquare);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(BLACK, enemyPieceSquare)));

        board = new Board(game);
        move = new DiagonalMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
        assertThat(move.path()).doesNotContain(nextSquareAfterEnemyPiece);
    }

    @Test
    public void rightPathBehindEndsWhereThereIsAFriendlyPieceOnTheWay() {
        Square square = new Square(Letter.B, 6);
        Square friendlyPieceSquare = new Square(Letter.E, 3);
        Square nextSquareAfterFriendlyPiece = new Square(Letter.F, 2);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(WHITE, friendlyPieceSquare)));

        board = new Board(game);
        move = new DiagonalMove(board, square);

        assertThat(move.path()).doesNotContain(friendlyPieceSquare);
        assertThat(move.path()).doesNotContain(nextSquareAfterFriendlyPiece);
    }

    @Test
    public void rightPathBehindEndsOnSquareFilledWithEnemyPiece() {
        Square square = new Square(Letter.F, 5);
        Square enemyPieceSquare = new Square(Letter.D, 3);
        Square nextSquareAfterEnemyPiece = new Square(Letter.C, 2);

        List<Square> squares = new ArrayList<>();
        squares.add(enemyPieceSquare);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(BLACK, enemyPieceSquare)));

        board = new Board(game);
        move = new DiagonalMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
        assertThat(move.path()).doesNotContain(nextSquareAfterEnemyPiece);
    }
}