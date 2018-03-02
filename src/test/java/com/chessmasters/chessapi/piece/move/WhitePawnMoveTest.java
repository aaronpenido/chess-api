package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Pawn;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class WhitePawnMoveTest {

    private WhitePawnMove move;
    private Pawn pawn;
    private Board board;
    
    @Test
    public void oneSquareAhead() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(WHITE, square);
        board = new Board(Collections.singletonList(pawn));
        move = new WhitePawnMove(board, square);
        
        assertThat(move.path()).containsExactlyInAnyOrder(new Square(Letter.E, 5));
    }

    @Test
    public void doesNotMoveWhenItsInTopBorder() {
        Square square = new Square(Letter.E, 8);
        pawn = new Pawn(WHITE, square);
        board = new Board(Collections.singletonList(pawn));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).isEmpty();
    }

    @Test
    public void twoSquaresAheadOnlyWhenItsInSecondRow() {
        Square square = new Square(Letter.E, 2);
        pawn = new Pawn(WHITE, square);
        board = new Board(Collections.singletonList(pawn));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).contains(new Square(Letter.E, 4));
    }

    @Test
    public void doesNotMoveTwoSquaresAheadWhenItsNotInSecondRow() {
        Square square = new Square(Letter.E, 3);
        pawn = new Pawn(WHITE, square);
        board = new Board(Collections.singletonList(pawn));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).doesNotContain(new Square(Letter.E, 5));
    }

    @Test
    public void leftDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        Square square = new Square(Letter.E, 4);
        Square enemySquare = new Square(Letter.D, 5);
        pawn = new Pawn(WHITE, square);
        Pawn enemy = new Pawn(BLACK, enemySquare);
        board = new Board(Arrays.asList(pawn, enemy));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).contains(enemySquare);
    }

    @Test
    public void rightDiagonalSquareWhenThereIsAnEnemyPieceOnIt() {
        Square square = new Square(Letter.E, 4);
        Square enemySquare = new Square(Letter.F, 5);
        pawn = new Pawn(WHITE, square);
        Pawn enemy = new Pawn(BLACK, enemySquare);
        board = new Board(Arrays.asList(pawn, enemy));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).contains(enemySquare);
    }

    @Test
    public void doesNotMoveWhenThereIsAFriendlyPieceAhead() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(WHITE, square);
        Pawn friendly = new Pawn(WHITE, new Square(Letter.E, 5));
        board = new Board(Arrays.asList(pawn, friendly));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).isEmpty();
    }

    @Test
    public void doesNotMoveWhenThereIsAnEnemyPieceAhead() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(WHITE, square);
        Pawn enemy = new Pawn(BLACK, new Square(Letter.E, 5));
        board = new Board(Arrays.asList(pawn, enemy));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).isEmpty();
    }

    @Test
    public void doesNotMoveTwoSquaresAheadAtInitialPositionIfThereIsAFriendlyPieceInTheWay() {
        Square square = new Square(Letter.E, 2);
        pawn = new Pawn(WHITE, square);
        Pawn friendly = new Pawn(WHITE, new Square(Letter.E, 4));
        board = new Board(Arrays.asList(pawn, friendly));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).containsExactlyInAnyOrder(new Square(Letter.E, 3));
    }

    @Test
    public void doesNotMoveTwoSquaresAheadAtInitialPositionIfThereIsAnEnemyPieceInTheWay() {
        Square square = new Square(Letter.E, 2);
        pawn = new Pawn(WHITE, square);
        Pawn enemy = new Pawn(BLACK, new Square(Letter.E, 4));
        board = new Board(Arrays.asList(pawn, enemy));
        move = new WhitePawnMove(board, square);

        assertThat(move.path()).containsExactlyInAnyOrder(new Square(Letter.E, 3));
    }

    @Test
    public void attackMoves() {
        Square square = new Square(Letter.E, 4);
        pawn = new Pawn(WHITE, square);
        board = new Board(Collections.singletonList(pawn));
        move = new WhitePawnMove(board, square);

        assertThat(pawn.attackMoves(board)).containsExactlyInAnyOrder(
                new Square(Letter.D, 5),
                new Square(Letter.F, 5));
    }
}