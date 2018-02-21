package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DiagonalMoveTest {

    private DiagonalMove move;

    @Test
    public void movesAheadToLeftDiagonal() {
        move = new DiagonalMove(new Square('D', 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('C', 6));
        squares.add(new Square('B', 7));
        squares.add(new Square('A', 8));


        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadToRightDiagonal() {
        move = new DiagonalMove(new Square('D', 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('E', 6));
        squares.add(new Square('F', 7));
        squares.add(new Square('G', 8));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindToLeftDiagonal() {
        move = new DiagonalMove(new Square('D', 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 2));
        squares.add(new Square('B', 3));
        squares.add(new Square('C', 4));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindToRightDiagonal() {
        move = new DiagonalMove(new Square('D', 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('E', 4));
        squares.add(new Square('F', 3));
        squares.add(new Square('G', 2));
        squares.add(new Square('H', 1));

        assertThat(move.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadOnlyToRightDiagonalWhenItsInLeftBottomBorder() {
        move = new DiagonalMove(new Square('A', 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('B', 2));
        squares.add(new Square('C', 3));
        squares.add(new Square('D', 4));
        squares.add(new Square('E', 5));
        squares.add(new Square('F', 6));
        squares.add(new Square('G', 7));
        squares.add(new Square('H', 8));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesAheadOnlyToLeftDiagonalWhenItsInRightBottomBorder() {
        move = new DiagonalMove(new Square('H', 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 8));
        squares.add(new Square('B', 7));
        squares.add(new Square('C', 6));
        squares.add(new Square('D', 5));
        squares.add(new Square('E', 4));
        squares.add(new Square('F', 3));
        squares.add(new Square('G', 2));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindOnlyToRightDiagonalWhenItsInLeftTopBorder() {
        move = new DiagonalMove(new Square('A', 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('B', 7));
        squares.add(new Square('C', 6));
        squares.add(new Square('D', 5));
        squares.add(new Square('E', 4));
        squares.add(new Square('F', 3));
        squares.add(new Square('G', 2));
        squares.add(new Square('H', 1));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesBehindOnlyToLeftDiagonalWhenItsInRightTopBorder() {
        move = new DiagonalMove(new Square('H', 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 1));
        squares.add(new Square('B', 2));
        squares.add(new Square('C', 3));
        squares.add(new Square('D', 4));
        squares.add(new Square('E', 5));
        squares.add(new Square('F', 6));
        squares.add(new Square('G', 7));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesOnlyAheadWhenItsInBottomBorder() {
        move = new DiagonalMove(new Square('E', 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 5));
        squares.add(new Square('B', 4));
        squares.add(new Square('C', 3));
        squares.add(new Square('D', 2));
        squares.add(new Square('F', 2));
        squares.add(new Square('G', 3));
        squares.add(new Square('H', 4));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesOnlyBehindWhenItsInTopBorder() {
        move = new DiagonalMove(new Square('E', 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 4));
        squares.add(new Square('B', 5));
        squares.add(new Square('C', 6));
        squares.add(new Square('D', 7));
        squares.add(new Square('F', 7));
        squares.add(new Square('G', 6));
        squares.add(new Square('H', 5));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesToAllDirectionsWhenItsInCenter() {
        move = new DiagonalMove(new Square('E', 4));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('B', 1));
        squares.add(new Square('C', 2));
        squares.add(new Square('D', 3));
        squares.add(new Square('F', 5));
        squares.add(new Square('G', 6));
        squares.add(new Square('H', 7));

        squares.add(new Square('A', 8));
        squares.add(new Square('B', 7));
        squares.add(new Square('C', 6));
        squares.add(new Square('D', 5));

        squares.add(new Square('F', 3));
        squares.add(new Square('G', 2));
        squares.add(new Square('H', 1));

        assertThat(move.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}