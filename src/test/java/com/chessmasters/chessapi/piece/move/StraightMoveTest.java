package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Rook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StraightMoveTest {

    private StraightMove move;
    private Board board;
    @Mock
    private Game game;

    @Test
    public void leftPath() {
        move = createStraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 4));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.C, 4));
        squares.add(new Square(Letter.D, 4));

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void rightPath() {
        move = createStraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.G, 4));
        squares.add(new Square(Letter.H, 4));

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void aheadPath() {
        move = createStraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.E, 6));
        squares.add(new Square(Letter.E, 7));
        squares.add(new Square(Letter.E, 8));

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void behindPath() {
        move = createStraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 1));
        squares.add(new Square(Letter.E, 2));
        squares.add(new Square(Letter.E, 3));

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathDoesNotContainsActualSquare() {
        Square square = new Square(Letter.E, 4);
        move = createStraightMove(square);

        assertThat(move.path()).doesNotContain(square);
    }

    @Test
    public void leftPathEndsWhereThereIsAFriendlyPieceOnTheWay() {
        Square square = new Square(Letter.H, 4);
        Square friendlyPieceSquare = new Square(Letter.E, 4);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.G, 4));

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(WHITE, friendlyPieceSquare)));

        board = new Board(game);
        move = new StraightMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void leftPathEndsOnSquareFilledWithEnemyPiece() {
        Square square = new Square(Letter.H, 4);
        Square enemyPieceSquare = new Square(Letter.E, 4);

        List<Square> squares = new ArrayList<>();
        squares.add(enemyPieceSquare);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(BLACK, enemyPieceSquare)));

        board = new Board(game);
        move = new StraightMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void rightPathEndsWhereThereIsAFriendlyPieceOnTheWay() {
        Square square = new Square(Letter.A, 4);
        Square friendlyPieceSquare = new Square(Letter.D, 4);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.C, 4));

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(WHITE, friendlyPieceSquare)));

        board = new Board(game);
        move = new StraightMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void rightPathEndsOnSquareFilledWithEnemyPiece() {
        Square square = new Square(Letter.A, 4);
        Square enemyPieceSquare = new Square(Letter.D, 4);

        List<Square> squares = new ArrayList<>();
        squares.add(enemyPieceSquare);

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(BLACK, enemyPieceSquare)));

        board = new Board(game);
        move = new StraightMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void aheadPathEndsWhereThereIsAFriendlyPieceOnTheWay() {
        Square square = new Square(Letter.E, 4);
        Square friendlyPieceSquare = new Square(Letter.E, 7);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.E, 6));

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(WHITE, friendlyPieceSquare)));

        board = new Board(game);
        move = new StraightMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void aheadPathEndsOnSquareFilledWithEnemyPiece() {
        Square square = new Square(Letter.E, 4);
        Square enemyPieceSquare = new Square(Letter.E, 7);

        List<Square> squares = new ArrayList<>();

        squares.add(enemyPieceSquare);
        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(BLACK, enemyPieceSquare)));

        board = new Board(game);
        move = new StraightMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void behindPathEndsWhereThereIsAFriendlyPieceOnTheWay() {
        Square square = new Square(Letter.E, 4);
        Square friendlyPieceSquare = new Square(Letter.E, 1);

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.E, 2));
        squares.add(new Square(Letter.E, 3));

        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(WHITE, friendlyPieceSquare)));

        board = new Board(game);
        move = new StraightMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void behindPathEndsOnSquareFilledWithEnemyPiece() {
        Square square = new Square(Letter.E, 4);
        Square enemyPieceSquare = new Square(Letter.E, 1);

        List<Square> squares = new ArrayList<>();

        squares.add(enemyPieceSquare);
        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, square),
                new Rook(BLACK, enemyPieceSquare)));


        board = new Board(game);
        move = new StraightMove(board, square);

        assertThat(move.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    private StraightMove createStraightMove(Square square) {
        when(game.getPieces()).thenReturn(Collections.singletonList(new Rook(WHITE, square)));
        board = new Board(game);
        return new StraightMove(board, square);
    }
}