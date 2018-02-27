package com.chessmasters.chessapi;

import com.chessmasters.chessapi.exception.InvalidMoveException;
import com.chessmasters.chessapi.piece.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    private Board board;

    @Test
    public void throwInvalidMoveExceptionWhenPawnMovesToDiagonalSquareWithoutPieceToCapture() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.B, 3);
        board = new Board(Collections.singletonList(new Pawn(WHITE, from)));

        assertThatThrownBy(() -> board.movePiece(from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void pieceMovesLeavingPreviousSquareEmpty() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        board = new Board(Collections.singletonList(new Pawn(WHITE, from)));

        board.movePiece(from, destination);

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

        board.movePiece(from, destination);

        assertThat(board.getPieces().size()).isEqualTo(1);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPawnTriesToMoveAheadToFilledSquare() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        board = new Board(Arrays.asList(
                new Pawn(WHITE, from),
                new Pawn(BLACK, destination)));

        assertThatThrownBy(() -> board.movePiece(from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPieceTriesToMoveToFilledSquareWithSamePieceColor() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        board = new Board(Arrays.asList(
                new Rook(WHITE, from),
                new Rook(WHITE, destination)));

        assertThatThrownBy(() -> board.movePiece(from, destination))
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

        assertThatThrownBy(() -> board.movePiece(from, destination))
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

        assertThatThrownBy(() -> board.movePiece(from, destination))
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

        board.movePiece(from, destination);

        Piece knight = board.getPieceFromSquare(destination);

        assertThat(knight).isNotNull();
    }

    //TODO: castling
    //TODO: En passant
    //TODO: Pawn Promotion
    //TODO: Check
    //TODO: Check Mate
    //TODO: Stalemate
    //TODO: Draw
    //TODO: Piece can't move if king is or will be in check
}