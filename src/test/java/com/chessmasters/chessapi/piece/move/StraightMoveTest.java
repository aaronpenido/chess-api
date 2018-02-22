package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StraightMoveTest {

    @Test
    public void straightMovesLeft() {
        StraightMove move = new StraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 4));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.C, 4));
        squares.add(new Square(Letter.D, 4));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesRight() {
        StraightMove move = new StraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.G, 4));
        squares.add(new Square(Letter.H, 4));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesAhead() {
        StraightMove move = new StraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.E, 6));
        squares.add(new Square(Letter.E, 7));
        squares.add(new Square(Letter.E, 8));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightMovesBehind() {
        StraightMove move = new StraightMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 1));
        squares.add(new Square(Letter.E, 2));
        squares.add(new Square(Letter.E, 3));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void straightDoesNotMoveToActualSquare() {
        Square square = new Square(Letter.E, 4);
        StraightMove move = new StraightMove(square);

        assertThat(move.moves()).doesNotContain(square);
    }
}