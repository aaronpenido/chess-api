package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DiagonalMoveTest {

    private DiagonalMove move;

    @Test
    public void movesAheadToLeftDiagonal() {
        move = new DiagonalMove(new Square(Letter.D, 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.B, 7));
        squares.add(new Square(Letter.A, 8));


        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadToRightDiagonal() {
        move = new DiagonalMove(new Square(Letter.D, 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 6));
        squares.add(new Square(Letter.F, 7));
        squares.add(new Square(Letter.G, 8));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindToLeftDiagonal() {
        move = new DiagonalMove(new Square(Letter.D, 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 2));
        squares.add(new Square(Letter.B, 3));
        squares.add(new Square(Letter.C, 4));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindToRightDiagonal() {
        move = new DiagonalMove(new Square(Letter.D, 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.E, 4));
        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));
        squares.add(new Square(Letter.H, 1));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadOnlyToRightDiagonalWhenItsInLeftBottomBorder() {
        move = new DiagonalMove(new Square(Letter.A, 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.B, 2));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.D, 4));
        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.F, 6));
        squares.add(new Square(Letter.G, 7));
        squares.add(new Square(Letter.H, 8));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadOnlyToLeftDiagonalWhenItsInRightBottomBorder() {
        move = new DiagonalMove(new Square(Letter.H, 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 8));
        squares.add(new Square(Letter.B, 7));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.D, 5));
        squares.add(new Square(Letter.E, 4));
        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindOnlyToRightDiagonalWhenItsInLeftTopBorder() {
        move = new DiagonalMove(new Square(Letter.A, 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.B, 7));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.D, 5));
        squares.add(new Square(Letter.E, 4));
        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));
        squares.add(new Square(Letter.H, 1));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindOnlyToLeftDiagonalWhenItsInRightTopBorder() {
        move = new DiagonalMove(new Square(Letter.H, 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 1));
        squares.add(new Square(Letter.B, 2));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.D, 4));
        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.F, 6));
        squares.add(new Square(Letter.G, 7));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesOnlyAheadWhenItsInBottomBorder() {
        move = new DiagonalMove(new Square(Letter.E, 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 5));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.C, 3));
        squares.add(new Square(Letter.D, 2));
        squares.add(new Square(Letter.F, 2));
        squares.add(new Square(Letter.G, 3));
        squares.add(new Square(Letter.H, 4));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesOnlyBehindWhenItsInTopBorder() {
        move = new DiagonalMove(new Square(Letter.E, 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.A, 4));
        squares.add(new Square(Letter.B, 5));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.D, 7));
        squares.add(new Square(Letter.F, 7));
        squares.add(new Square(Letter.G, 6));
        squares.add(new Square(Letter.H, 5));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesToAllDirectionsWhenItsInCenter() {
        move = new DiagonalMove(new Square(Letter.E, 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(Letter.B, 1));
        squares.add(new Square(Letter.C, 2));
        squares.add(new Square(Letter.D, 3));
        squares.add(new Square(Letter.F, 5));
        squares.add(new Square(Letter.G, 6));
        squares.add(new Square(Letter.H, 7));

        squares.add(new Square(Letter.A, 8));
        squares.add(new Square(Letter.B, 7));
        squares.add(new Square(Letter.C, 6));
        squares.add(new Square(Letter.D, 5));

        squares.add(new Square(Letter.F, 3));
        squares.add(new Square(Letter.G, 2));
        squares.add(new Square(Letter.H, 1));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}