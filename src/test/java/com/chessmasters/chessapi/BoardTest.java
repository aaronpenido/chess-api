package com.chessmasters.chessapi;

import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.exception.InvalidMoveException;
import com.chessmasters.chessapi.piece.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

import static com.chessmasters.chessapi.enums.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    private Board board;
    private Player whitePlayer = new Player(WHITE);

    @Test
    public void throwInvalidMoveExceptionWhenPawnMovesToDiagonalSquareWithoutPieceToCapture() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.B, 3);
        board = new Board(Collections.singletonList(new Pawn(WHITE, from)));

        assertThatThrownBy(() -> board.movePiece(whitePlayer, from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void pieceMovesLeavingPreviousSquareEmpty() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        board = new Board(Collections.singletonList(new Pawn(WHITE, from)));

        board.movePiece(whitePlayer, from, destination);

        Piece piece = board.getPieceFromSquare(from);

        assertThat(piece).isNull();
    }

    @Test
    public void pieceIsRemovedFromBoardWhenCaptured() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        board = new Board(Arrays.asList(
                new Rook(WHITE, from),
                new Rook(BLACK, destination)));

        board.movePiece(whitePlayer, from, destination);

        assertThat(board.getPieces().size()).isEqualTo(1);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPawnTriesToMoveAheadToFilledSquare() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        board = new Board(Arrays.asList(
                new Pawn(WHITE, from),
                new Pawn(BLACK, destination)));

        assertThatThrownBy(() -> board.movePiece(whitePlayer, from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPieceTriesToMoveToFilledSquareWithSamePieceColor() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        board = new Board(Arrays.asList(
                new Rook(WHITE, from),
                new Rook(WHITE, destination)));

        assertThatThrownBy(() -> board.movePiece(whitePlayer, from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenThereIsAPieceOnTheWayToDiagonalDestinationSquare() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.A, 8);
        Square between = new Square(Letter.C, 6);
        board = new Board(Arrays.asList(
                new Bishop(WHITE, from),
                new Pawn(WHITE, between)));

        assertThatThrownBy(() -> board.movePiece(whitePlayer, from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenThereIsAPieceOnTheWayToStraightDestinationSquare() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.E, 8);
        Square between = new Square(Letter.E, 6);
        board = new Board(Arrays.asList(
                new Rook(WHITE, from),
                new Pawn(WHITE, between)));

        assertThatThrownBy(() -> board.movePiece(whitePlayer, from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void knightMovesEvenIfThereIsAPieceOnTheWay() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 6);
        Square between = new Square(Letter.E, 5);
        board = new Board(Arrays.asList(
                new Knight(WHITE, from),
                new Pawn(WHITE, between)));

        board.movePiece(whitePlayer, from, destination);

        Piece knight = board.getPieceFromSquare(destination);

        assertThat(knight).isNotNull();
    }

    @Test
    public void throwInvalidMoveExceptionWhenMovePieceAfterAnotherWithSameColor() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.E, 5);
        Square secondDestination = new Square(Letter.E, 6);

        board = new Board(Collections.singletonList(new Pawn(WHITE, from)));

        board.movePiece(whitePlayer, from, destination);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, destination, secondDestination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPlayerTriesToMoveOpponentsPiece() {
        Square from = new Square(Letter.E, 2);
        Square destination = new Square(Letter.E, 3);
        board = new Board(Collections.singletonList(new Pawn(WHITE, from)));

        board.movePiece(whitePlayer, from, destination);

        Square nextMoveFrom = destination;
        Square nextMoveDestination = new Square(Letter.E, 4);

        assertThatThrownBy(() -> board.movePiece(new Player(BLACK), nextMoveFrom, nextMoveDestination))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessageContaining("It's opponent's turn");
    }
}