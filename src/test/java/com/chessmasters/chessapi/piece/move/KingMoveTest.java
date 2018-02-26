package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KingMoveTest {

    private KingMove move;
    private Board board;
    private List<Piece> pieces;

    @Test
    public void pathOnCenterWithoutOtherPieces() {
        Square square = new Square(Letter.E, 4);
        board = new Board(Collections.singletonList(new King(WHITE, square)));

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.D, 5));
        squares.add(new Square(Letter.E, 5));
        squares.add(new Square(Letter.F, 5));
        squares.add(new Square(Letter.D, 4));
        squares.add(new Square(Letter.F, 4));
        squares.add(new Square(Letter.D, 3));
        squares.add(new Square(Letter.E, 3));
        squares.add(new Square(Letter.F, 3));

        move = new KingMove(board, square);

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathOnBottomBorderWithoutOtherPieces() {
        Square square = new Square(Letter.E, 1);
        board = new Board(Collections.singletonList(new King(WHITE, square)));

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.D, 1));
        squares.add(new Square(Letter.F, 1));
        squares.add(new Square(Letter.D, 2));
        squares.add(new Square(Letter.E, 2));
        squares.add(new Square(Letter.F, 2));

        move = new KingMove(board, square);

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathOnTopBorderWithoutOtherPieces() {
        Square square = new Square(Letter.E, 8);
        board = new Board(Collections.singletonList(new King(WHITE, square)));

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.D, 8));
        squares.add(new Square(Letter.F, 8));
        squares.add(new Square(Letter.D, 7));
        squares.add(new Square(Letter.E, 7));
        squares.add(new Square(Letter.F, 7));

        move = new KingMove(board, square);

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathOnLeftBorderWithoutOtherPieces() {
        Square square = new Square(Letter.A, 4);
        board = new Board(Collections.singletonList(new King(WHITE, square)));

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.A, 3));
        squares.add(new Square(Letter.A, 5));
        squares.add(new Square(Letter.B, 5));
        squares.add(new Square(Letter.B, 4));
        squares.add(new Square(Letter.B, 3));

        move = new KingMove(board, square);

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathOnRightBorderWithoutOtherPieces() {
        Square square = new Square(Letter.H, 4);
        board = new Board(Collections.singletonList(new King(WHITE, square)));

        List<Square> squares = new ArrayList<>();
        squares.add(new Square(Letter.H, 3));
        squares.add(new Square(Letter.H, 5));
        squares.add(new Square(Letter.G, 5));
        squares.add(new Square(Letter.G, 4));
        squares.add(new Square(Letter.G, 3));

        move = new KingMove(board, square);

        assertThat(move.path()).containsExactlyInAnyOrder(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void pathDoesNotContainSquareFilledWithFriendlyPiece() {
        Square square = new Square(Letter.H, 4);
        Square pawnSquare = new Square(Letter.H, 4);

        board = new Board(Arrays.asList(
                new King(WHITE, square),
                new Pawn(WHITE, pawnSquare)));

        move = new KingMove(board, square);

        assertThat(move.path()).doesNotContain(pawnSquare);
    }

    @Test
    public void pathDoesNotContainSquareUnderAttackByEnemyBishop() {
        Square square = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 5);
        Square bishopSquare = new Square(Letter.C, 8);

        pieces = new ArrayList<>();
        pieces.add(new King(WHITE, square));
        pieces.add(new Bishop(BLACK, bishopSquare));
        board = new Board(pieces);

        move = new KingMove(board, square);

        assertThat(move.path()).doesNotContain(destination);
    }

    @Test
    public void pathDoesNotContainSquareUnderAttackByEnemyRook() {
        Square square = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 5);
        Square rookSquare = new Square(Letter.F, 1);

        pieces = new ArrayList<>();
        pieces.add(new King(WHITE, square));
        pieces.add(new Rook(BLACK, rookSquare));
        board = new Board(pieces);

        move = new KingMove(board, square);

        assertThat(move.path()).doesNotContain(destination);
    }

    @Test
    public void pathDoesNotContainSquareUnderAttackByEnemyKnight() {
        Square square = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 5);
        Square knightSquare = new Square(Letter.E, 3);

        pieces = new ArrayList<>();
        pieces.add(new King(WHITE, square));
        pieces.add(new Knight(BLACK, knightSquare));
        board = new Board(pieces);

        move = new KingMove(board, square);

        assertThat(move.path()).doesNotContain(destination);
    }

    @Test
    public void pathDoesNotContainSquaresUnderAttackByEnemyPawn() {
        Square square = new Square(Letter.E, 4);
        Square pawnSquare = new Square(Letter.E, 5);

        List<Square> attackedSquares = new ArrayList<>();
        attackedSquares.add(new Square(Letter.D, 4));
        attackedSquares.add(new Square(Letter.F, 4));

        pieces = new ArrayList<>();
        pieces.add(new King(WHITE, square));
        pieces.add(new Pawn(BLACK, pawnSquare));
        board = new Board(pieces);

        move = new KingMove(board, square);

        assertThat(move.path()).doesNotContainAnyElementsOf(attackedSquares);
    }

    @Test
    public void pathDoesNotContainSquaresUnderAttackByEnemyQueen() {
        Square square = new Square(Letter.E, 4);
        Square queenSquare = new Square(Letter.D, 6);

        List<Square> attackedSquares = new ArrayList<>();
        attackedSquares.add(new Square(Letter.D, 3));
        attackedSquares.add(new Square(Letter.D, 4));
        attackedSquares.add(new Square(Letter.D, 5));
        attackedSquares.add(new Square(Letter.E, 5));
        attackedSquares.add(new Square(Letter.F, 4));

        pieces = new ArrayList<>();
        pieces.add(new King(WHITE, square));
        pieces.add(new Queen(BLACK, queenSquare));
        board = new Board(pieces);

        move = new KingMove(board, square);

        assertThat(move.path()).doesNotContainAnyElementsOf(attackedSquares);
    }

    @Test
    public void pathDoesNotContainSquaresUnderAttackByEnemyKing() {
        Square square = new Square(Letter.E, 4);
        Square kingSquare = new Square(Letter.E, 6);

        List<Square> attackedSquares = new ArrayList<>();
        attackedSquares.add(new Square(Letter.D, 5));
        attackedSquares.add(new Square(Letter.E, 5));
        attackedSquares.add(new Square(Letter.F, 5));

        pieces = new ArrayList<>();
        pieces.add(new King(WHITE, square));
        pieces.add(new King(BLACK, kingSquare));
        board = new Board(pieces);

        move = new KingMove(board, square);

        assertThat(move.path()).doesNotContainAnyElementsOf(attackedSquares);
    }
}