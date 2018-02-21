package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Square;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class QueenTest {

    @Test
    public void queenHasValidMoves() {
        Queen queen = new Queen(WHITE, new Square('D', 1));

        assertThat(queen.moves()).isNotNull();
        assertThat(queen.moves()).isNotEmpty();
    }

    @Test
    public void queenMovesLeft() {
        Queen queen = new Queen(WHITE, new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 4));
        squares.add(new Square('B', 4));
        squares.add(new Square('C', 4));
        squares.add(new Square('D', 4));

        assertThat(queen.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void queenMovesRight() {
        Queen queen = new Queen(WHITE, new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('F', 4));
        squares.add(new Square('G', 4));
        squares.add(new Square('H', 4));

        assertThat(queen.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void queenMovesAhead() {
        Queen queen = new Queen(WHITE, new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('E', 5));
        squares.add(new Square('E', 6));
        squares.add(new Square('E', 7));
        squares.add(new Square('E', 8));

        assertThat(queen.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void queenMovesBehind() {
        Queen queen = new Queen(WHITE, new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('E', 1));
        squares.add(new Square('E', 2));
        squares.add(new Square('E', 3));

        assertThat(queen.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void queenMoves() {
    }

    @Test
    public void queenDoesNotMoveToActualSquare() {
        Queen queen = new Queen(WHITE, new Square('E', 4));

        assertThat(queen.moves()).doesNotContain(queen.getSquare());
    }
}