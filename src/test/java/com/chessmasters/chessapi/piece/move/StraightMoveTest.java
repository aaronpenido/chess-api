package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StraightMoveTest {

    @Test
    public void straightMovesLeft() {
        StraightMove move = new StraightMove(new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 4));
        squares.add(new Square('B', 4));
        squares.add(new Square('C', 4));
        squares.add(new Square('D', 4));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesRight() {
        StraightMove move = new StraightMove(new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('F', 4));
        squares.add(new Square('G', 4));
        squares.add(new Square('H', 4));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesAhead() {
        StraightMove move = new StraightMove(new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('E', 5));
        squares.add(new Square('E', 6));
        squares.add(new Square('E', 7));
        squares.add(new Square('E', 8));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesBehind() {
        StraightMove move = new StraightMove(new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('E', 1));
        squares.add(new Square('E', 2));
        squares.add(new Square('E', 3));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightDoesNotMoveToActualSquare() {
        Square square = new Square('E', 4);
        StraightMove move = new StraightMove(square);

        assertThat(move.moves()).doesNotContain(square);
    }
}