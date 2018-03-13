package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.entity.piece.Rook;
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
public class StraightMovementTest {

    StraightMovement movement;
    Board board;
    @Mock
    Game game;

    @Test
    public void leftPath() {
        movement = createStraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 4));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.C, 4));
        squares.add(new Square(Letter.D, 4));

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void rightPath() {
        movement = createStraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.G, 4));
        squares.add(new Square(Letter.H, 4));

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void aheadPath() {
        movement = createStraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.E, 6));
        squares.add(new Square(Letter.E, 7));
        squares.add(new Square(Letter.E, 8));

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void behindPath() {
        movement = createStraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 1));
        squares.add(new Square(Letter.E, 2));
        squares.add(new Square(Letter.E, 3));

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathDoesNotContainsActualSquare() {
        Square square = new Square(Letter.E, 4);
        movement = createStraightMove(square);

        assertThat(movement.path()).doesNotContain(square);
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
        movement = new StraightMovement(board, square);

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
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
        movement = new StraightMovement(board, square);

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
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
        movement = new StraightMovement(board, square);

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
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
        movement = new StraightMovement(board, square);

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
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
        movement = new StraightMovement(board, square);

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
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
        movement = new StraightMovement(board, square);

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
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
        movement = new StraightMovement(board, square);

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
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
        movement = new StraightMovement(board, square);

        assertThat(movement.path()).contains(squares.toArray(new Square[squares.size()]));
    }

    private StraightMovement createStraightMove(Square square) {
        when(game.getPieces()).thenReturn(Collections.singletonList(new Rook(WHITE, square)));
        board = new Board(game);
        return new StraightMovement(board, square);
    }
}