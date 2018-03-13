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
public class WhitePawnMovementTest {

    WhitePawnMovement movement;
    Pawn pawn;
    Board board;
    @Mock
    Game game;
    
    @Test
    public void oneSquareAhead() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(WHITE, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);
        
        assertThat(movement.path()).containsExactlyInAnyOrder(new Square(Letter.E, 5));
    }

    @Test
    public void doesNotMoveWhenItsInTopBorder() {
        Square square = new Square(Letter.E, 8);
        pawn = new Pawn(WHITE, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).isEmpty();
    }

    @Test
    public void twoSquaresAheadOnlyWhenItsInSecondRow() {
        Square square = new Square(Letter.E, 2);
        pawn = new Pawn(WHITE, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).contains(new Square(Letter.E, 4));
    }

    @Test
    public void doesNotMoveTwoSquaresAheadWhenItsNotInSecondRow() {
        Square square = new Square(Letter.E, 3);
        pawn = new Pawn(WHITE, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).doesNotContain(new Square(Letter.E, 5));
    }

    @Test
    public void leftDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        Square square = new Square(Letter.E, 4);
        Square enemySquare = new Square(Letter.D, 5);
        pawn = new Pawn(WHITE, square);
        Pawn enemy = new Pawn(BLACK, enemySquare);
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, enemy));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).contains(enemySquare);
    }

    @Test
    public void rightDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        Square square = new Square(Letter.E, 4);
        Square enemySquare = new Square(Letter.F, 5);
        pawn = new Pawn(WHITE, square);
        Pawn enemy = new Pawn(BLACK, enemySquare);
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, enemy));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).contains(enemySquare);
    }

    @Test
    public void doesNotMoveWhenThereIsAFriendlyPieceAhead() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(WHITE, square);
        Pawn friendly = new Pawn(WHITE, new Square(Letter.E, 5));
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, friendly));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).isEmpty();
    }

    @Test
    public void doesNotMoveWhenThereIsAnEnemyPieceAhead() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(WHITE, square);
        Pawn enemy = new Pawn(BLACK, new Square(Letter.E, 5));
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, enemy));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).isEmpty();
    }

    @Test
    public void doesNotMoveTwoSquaresAheadAtInitialPositionIfThereIsAFriendlyPieceInTheWay() {
        Square square = new Square(Letter.E, 2);
        pawn = new Pawn(WHITE, square);
        Pawn friendly = new Pawn(WHITE, new Square(Letter.E, 4));
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, friendly));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).containsExactlyInAnyOrder(new Square(Letter.E, 3));
    }

    @Test
    public void doesNotMoveTwoSquaresAheadAtInitialPositionIfThereIsAnEnemyPieceInTheWay() {
        Square square = new Square(Letter.E, 2);
        pawn = new Pawn(WHITE, square);
        Pawn enemy = new Pawn(BLACK, new Square(Letter.E, 4));
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, enemy));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(movement.path()).containsExactlyInAnyOrder(new Square(Letter.E, 3));
    }

    @Test
    public void attackMoves() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(WHITE, square);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);
        movement = new WhitePawnMovement(board, square);

        assertThat(pawn.attackMoves(board)).containsExactlyInAnyOrder(
                new Square(Letter.D, 5),
                new Square(Letter.F, 5));
    }
}