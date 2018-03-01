package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.chessmasters.chessapi.enums.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    private King king;
    private Board board;

    @Test
    public void kingHasValidMoves() {
        king = new King(WHITE, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(king));

        List<Square> path = king.moves(board);

        assertThat(path).isNotNull();
        assertThat(path).isNotEmpty();
    }

    @Test
    public void kingMovesOneSquareAhead() {
        king = new King(WHITE, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).contains(new Square(Letter.E, 2));
    }

    @Test
    public void kingMovesOneSquareAheadInLeftDiagonal() {
        king = new King(WHITE, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).contains(new Square(Letter.D, 2));
    }

    @Test
    public void kingMovesOneSquareAheadInRightDiagonal() {
        king = new King(WHITE, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).contains(new Square(Letter.F, 2));
    }

    @Test
    public void kingMovesOneSquareBehind() {
        king = new King(WHITE, new Square(Letter.E, 2));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).contains(new Square(Letter.E, 1));
    }

    @Test
    public void kingMovesOneSquareBehindInLeftDiagonal() {
        king = new King(WHITE, new Square(Letter.E, 2));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).contains(new Square(Letter.D, 1));
    }

    @Test
    public void kingMovesOneSquareBehindInRightDiagonal() {
        king = new King(WHITE, new Square(Letter.E, 2));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).contains(new Square(Letter.F, 1));
    }

    @Test
    public void kingMovesOneSquareLeft() {
        king = new King(WHITE, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).contains(new Square(Letter.D, 1));
    }

    @Test
    public void kingMovesOneSquareRight() {
        king = new King(WHITE, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).contains(new Square(Letter.F, 1));
    }

    @Test
    public void kingDoesNotMoveAheadWhenItsInTopBorder() {
        king = new King(WHITE, new Square(Letter.E, 8));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).doesNotContain(new Square(Letter.E, 9));
    }

    @Test
    public void kingDoesNotMoveBehindWhenItsInDownBorder() {
        king = new King(WHITE, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(king));

        assertThat(king.moves(board)).doesNotContain(new Square(Letter.E, 0));
    }

    @Test
    public void kingDoesNotMoveRightWhenItsInRightBorder() {
        king = new King(WHITE, new Square(Letter.H, 4));
        board = new Board(Collections.singletonList(king));

        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.H, 3));
        squares.add(new Square(Letter.H, 5));
        squares.add(new Square(Letter.G, 3));
        squares.add(new Square(Letter.G, 4));
        squares.add(new Square(Letter.G, 5));

        assertThat(king.moves(board)).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void kingDoesNotMoveLeftWhenItsInLeftBorder() {
        king = new King(WHITE, new Square(Letter.A, 4));
        board = new Board(Collections.singletonList(king));

        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 3));
        squares.add(new Square(Letter.A, 5));
        squares.add(new Square(Letter.B, 3));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.B, 5));

        assertThat(king.moves(board)).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}