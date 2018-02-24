package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StraightMoveTest {

    private StraightMove move;

    @Test
    public void straightMovesLeft() {
        move = new StraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 4));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.C, 4));
        squares.add(new Square(Letter.D, 4));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesRight() {
        move = new StraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.G, 4));
        squares.add(new Square(Letter.H, 4));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesAhead() {
        move = new StraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.E, 6));
        squares.add(new Square(Letter.E, 7));
        squares.add(new Square(Letter.E, 8));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesBehind() {
        move = new StraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 1));
        squares.add(new Square(Letter.E, 2));
        squares.add(new Square(Letter.E, 3));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightDoesNotMoveToActualSquare() {
        Square square = new Square(Letter.E, 4);
        move = new StraightMove(square);

        assertThat(move.moves()).doesNotContain(square);
    }

    @Test
    public void squarePathIsInValid() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.B, 8);
        move = new StraightMove(from);

        assertThat(move.path(destination)).isEmpty();
    }

    @Test
    public void aheadPathIsValid() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.E, 8);
        move = new StraightMove(from);

        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.E, 6));
        squares.add(new Square(Letter.E, 7));

        assertThat(move.path(destination)).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void behindPathIsValid() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.E, 1);
        move = new StraightMove(from);

        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 2));
        squares.add(new Square(Letter.E, 3));

        assertThat(move.path(destination)).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void leftPathIsValid() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.A, 4);
        move = new StraightMove(from);

        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.C, 4));
        squares.add(new Square(Letter.D, 4));

        assertThat(move.path(destination)).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void rightPathIsValid() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.H, 4);
        move = new StraightMove(from);

        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.G, 4));

        assertThat(move.path(destination)).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}