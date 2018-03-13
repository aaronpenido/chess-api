package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.entity.piece.Pawn;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlackPawnMovementTest {

    BlackPawnMovement movement;
    Pawn pawn;
    Board board;
    @Mock
    Game game;

    @Test
    public void oneSquareAhead() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(BLACK, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(new Square(Letter.E, 3));
    }

    @Test
    public void doesNotMoveWhenItsInTopBorder() {
        Square square = new Square(Letter.E, 1);
        pawn = new Pawn(BLACK, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).isEmpty();
    }

    @Test
    public void twoSquaresAheadOnlyWhenItsInSeventhRow() {
        Square square = new Square(Letter.E, 7);
        pawn = new Pawn(BLACK, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).contains(new Square(Letter.E, 5));
    }

    @Test
    public void doesNotMoveTwoSquaresAheadWhenItsNotInSeventhRow() {
        Square square = new Square(Letter.E, 6);
        pawn = new Pawn(BLACK, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).doesNotContain(new Square(Letter.E, 4));
    }

    @Test
    public void leftDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(BLACK, square);
        Square enemySquare = new Square(Letter.F, 3);
        Pawn enemy = new Pawn(WHITE, enemySquare);
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, enemy));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).contains(enemySquare);
    }

    @Test
    public void blackMovesToRightDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(BLACK, square);
        Square enemySquare = new Square(Letter.D, 3);
        Pawn enemy = new Pawn(WHITE, enemySquare);
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, enemy));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).contains(enemySquare);
    }

    @Test
    public void doesNotMoveWhenThereIsAnEnemyPieceAhead() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(BLACK, square);
        Pawn enemy = new Pawn(WHITE, new Square(Letter.E, 3));
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, enemy));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).isEmpty();
    }

    @Test
    public void doesNotMoveTwoSquaresAheadAtInitialPositionIfThereIsAFriendlyPieceInTheWay() {
        Square square = new Square(Letter.E, 7);
        pawn = new Pawn(BLACK, square);
        Pawn friendly = new Pawn(BLACK, new Square(Letter.E, 5));
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, friendly));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(new Square(Letter.E, 6));
    }

    @Test
    public void doesNotMoveTwoSquaresAheadAtInitialPositionIfThereIsAnEnemyPieceInTheWay() {
        Square square = new Square(Letter.E, 7);
        pawn = new Pawn(BLACK, square);
        Pawn enemy = new Pawn(WHITE, new Square(Letter.E, 5));
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, enemy));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.moves(board)).containsExactlyInAnyOrder(new Square(Letter.E, 6));
    }

    @Test
    public void attackMoves() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(BLACK, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new BlackPawnMovement(board, square);

        assertThat(pawn.attackMoves(board)).containsExactlyInAnyOrder(
                new Square(Letter.D, 3),
                new Square(Letter.F, 3));
    }
}