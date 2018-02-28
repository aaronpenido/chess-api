package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Knight;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.chessmasters.chessapi.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class KnightMoveTest {

    private Board board;
    private KnightMove move;
    
    @Test
    public void knightMovesLeftAhead() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.D, 6));
        expected.add(new Square(Letter.C, 5));

        assertThat(move.path()).contains(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightMovesLeftBehind() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.C, 3));
        expected.add(new Square(Letter.D, 2));

        assertThat(move.path()).contains(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightMovesRightAhead() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.F, 6));
        expected.add(new Square(Letter.G, 5));

        assertThat(move.path()).contains(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightMovesRightBehind() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.F, 2));
        expected.add(new Square(Letter.G, 3));

        assertThat(move.path()).contains(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightMovesMovesToAllDirectionsInCenter() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.D, 6));
        expected.add(new Square(Letter.C, 5));
        expected.add(new Square(Letter.C, 3));
        expected.add(new Square(Letter.D, 2));
        expected.add(new Square(Letter.F, 6));
        expected.add(new Square(Letter.G, 5));
        expected.add(new Square(Letter.F, 2));
        expected.add(new Square(Letter.G, 3));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightMovesOnlyRightWhenItsOnLeftBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());

        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.B, 6));
        expected.add(new Square(Letter.C, 5));
        expected.add(new Square(Letter.C, 3));
        expected.add(new Square(Letter.B, 2));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightMovesOnlyLeftWhenItsOnRightBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.H, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());

        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.G, 6));
        expected.add(new Square(Letter.F, 5));
        expected.add(new Square(Letter.F, 3));
        expected.add(new Square(Letter.G, 2));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesOnLeftWhenItsOneSquareNearLeftBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.B, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());

        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.A, 6));
        expected.add(new Square(Letter.A, 2));

        assertThat(move.path()).contains(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesOnRightWhenItsOneSquareNearRightBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.G, 4));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());

        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.H, 6));
        expected.add(new Square(Letter.H, 2));

        assertThat(move.path()).contains(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyMoveAheadWhenItsOnBottomBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 1));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());

        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.D, 3));
        expected.add(new Square(Letter.C, 2));
        expected.add(new Square(Letter.F, 3));
        expected.add(new Square(Letter.G, 2));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyMoveBehindWhenItsOnTopBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 8));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());

        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.C, 7));
        expected.add(new Square(Letter.D, 6));
        expected.add(new Square(Letter.F, 6));
        expected.add(new Square(Letter.G, 7));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesBehindWhenItsOneSquareNearBottomBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 2));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());

        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.C, 1));
        expected.add(new Square(Letter.G, 1));
        expected.add(new Square(Letter.D, 4));
        expected.add(new Square(Letter.C, 3));
        expected.add(new Square(Letter.F, 4));
        expected.add(new Square(Letter.G, 3));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesAheadWhenItsOneSquareNearTopBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 7));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());

        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.C, 8));
        expected.add(new Square(Letter.G, 8));
        expected.add(new Square(Letter.D, 5));
        expected.add(new Square(Letter.C, 6));
        expected.add(new Square(Letter.F, 5));
        expected.add(new Square(Letter.G, 6));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesWhenItsOnLeftBottomBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 1));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.B, 3));
        expected.add(new Square(Letter.C, 2));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesWhenItsOnRightBottomBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.H, 1));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.G, 3));
        expected.add(new Square(Letter.F, 2));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesWhenItsOnLeftTopBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 8));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.B, 6));
        expected.add(new Square(Letter.C, 7));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }

    @Test
    public void knightHasOnlyTwoMovesWhenItsOnRightTopBorder() {
        Knight knight = new Knight(WHITE, new Square(Letter.H, 8));
        board = new Board(Collections.singletonList(knight));
        move = new KnightMove(board, knight.getSquare());
        
        List<Square> expected = new ArrayList<>();
        expected.add(new Square(Letter.G, 6));
        expected.add(new Square(Letter.F, 7));

        assertThat(move.path()).containsExactlyInAnyOrder(expected.toArray(new Square[expected.size()]));
    }
}