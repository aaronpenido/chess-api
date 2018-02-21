package com.chessmasters.chessapi;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BishopTest {

    private Bishop bishop;

    @Test
    public void bishopHasValidMoves() {
        bishop = new Bishop(WHITE, new Square('D', 5));

        assertThat(bishop.moves()).isNotNull();
        assertThat(bishop.moves()).isNotEmpty();
    }

    @Test
    public void bishopMovesAheadToLeftDiagonal() {
        bishop = new Bishop(WHITE, new Square('D', 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('C', 6));
        squares.add(new Square('B', 7));
        squares.add(new Square('A', 8));


        assertThat(bishop.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesAheadToRightDiagonal() {
        bishop = new Bishop(WHITE, new Square('D', 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('E', 6));
        squares.add(new Square('F', 7));
        squares.add(new Square('G', 8));

        assertThat(bishop.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesBehindToLeftDiagonal() {
        bishop = new Bishop(WHITE, new Square('D', 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 2));
        squares.add(new Square('B', 3));
        squares.add(new Square('C', 4));

        assertThat(bishop.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesBehindToRightDiagonal() {
        bishop = new Bishop(WHITE, new Square('D', 5));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('E', 4));
        squares.add(new Square('F', 3));
        squares.add(new Square('G', 2));
        squares.add(new Square('H', 1));

        assertThat(bishop.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesAheadOnlyToRightDiagonalWhenItsInLeftBottomBorder() {
        bishop = new Bishop(WHITE, new Square('A', 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('B', 2));
        squares.add(new Square('C', 3));
        squares.add(new Square('D', 4));
        squares.add(new Square('E', 5));
        squares.add(new Square('F', 6));
        squares.add(new Square('G', 7));
        squares.add(new Square('H', 8));

        assertThat(bishop.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesAheadOnlyToLeftDiagonalWhenItsInRightBottomBorder() {
        bishop = new Bishop(WHITE, new Square('H', 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 8));
        squares.add(new Square('B', 7));
        squares.add(new Square('C', 6));
        squares.add(new Square('D', 5));
        squares.add(new Square('E', 4));
        squares.add(new Square('F', 3));
        squares.add(new Square('G', 2));

        assertThat(bishop.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesBehindOnlyToRightDiagonalWhenItsInLeftTopBorder() {
        bishop = new Bishop(WHITE, new Square('A', 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('B', 7));
        squares.add(new Square('C', 6));
        squares.add(new Square('D', 5));
        squares.add(new Square('E', 4));
        squares.add(new Square('F', 3));
        squares.add(new Square('G', 2));
        squares.add(new Square('H', 1));

        assertThat(bishop.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesBehindOnlyToLeftDiagonalWhenItsInRightTopBorder() {
        bishop = new Bishop(WHITE, new Square('H', 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 1));
        squares.add(new Square('B', 2));
        squares.add(new Square('C', 3));
        squares.add(new Square('D', 4));
        squares.add(new Square('E', 5));
        squares.add(new Square('F', 6));
        squares.add(new Square('G', 7));

        assertThat(bishop.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesOnlyAheadWhenItsInBottomBorder() {
        bishop = new Bishop(WHITE, new Square('E', 1));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 5));
        squares.add(new Square('B', 4));
        squares.add(new Square('C', 3));
        squares.add(new Square('D', 2));
        squares.add(new Square('F', 2));
        squares.add(new Square('G', 3));
        squares.add(new Square('H', 4));

        assertThat(bishop.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesOnlyBehindWhenItsInTopBorder() {
        bishop = new Bishop(WHITE, new Square('E', 8));
        List<Square> squares = new ArrayList<>();

        squares.add(new Square('A', 4));
        squares.add(new Square('B', 5));
        squares.add(new Square('C', 6));
        squares.add(new Square('D', 7));
        squares.add(new Square('F', 7));
        squares.add(new Square('G', 6));
        squares.add(new Square('H', 5));

        assertThat(bishop.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void bishopMovesToAllDirectionsWhenItsInCenter() {
        bishop = new Bishop(WHITE, new Square('E', 4));
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

        assertThat(bishop.moves()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }
}