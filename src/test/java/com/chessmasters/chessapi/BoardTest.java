package com.chessmasters.chessapi;

import com.chessmasters.chessapi.exception.InvalidMoveException;
import com.chessmasters.chessapi.piece.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    private List<Piece> pieces;
    private Board board;

    @Test
    public void throwInvalidMoveExceptionWhenPawnMovesToDiagonalSquareWithoutPieceToCapture() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.B, 3);
        pieces = new ArrayList<>();
        pieces.add(new Pawn(WHITE, from));
        board = new Board(pieces);

        assertThatThrownBy(() -> board.movePiece(from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void pieceMovesLeavingPreviousSquareEmpty() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        pieces = new ArrayList<>();
        pieces.add(new Pawn(WHITE, from));
        board = new Board(pieces);

        board.movePiece(from, destination);

        Piece piece = getPieceFromSquare(from);

        assertThat(piece).isNull();
    }

    @Test
    public void pieceIsRemovedFromBoardWhenCaptured() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        pieces = new ArrayList<>();
        pieces.add(new Rook(WHITE, from));
        pieces.add(new Rook(BLACK, destination));
        board = new Board(pieces);

        board.movePiece(from, destination);

        assertThat(board.getPieces().size()).isEqualTo(1);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPawnTriesToMoveAheadToFilledSquare() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        pieces = new ArrayList<>();
        pieces.add(new Pawn(WHITE, from));
        pieces.add(new Pawn(BLACK, destination));
        board = new Board(pieces);

        assertThatThrownBy(() -> board.movePiece(from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPieceTriesToMoveToFilledSquareWithSamePieceColor() {
        Square from = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        pieces = new ArrayList<>();
        pieces.add(new Rook(WHITE, from));
        pieces.add(new Rook(WHITE, destination));
        board = new Board(pieces);

        assertThatThrownBy(() -> board.movePiece(from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenThereIsAPieceOnTheWayToDiagonalDestinationSquare() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.A, 8);
        Square between = new Square(Letter.C, 6);
        pieces = new ArrayList<>();
        pieces.add(new Bishop(WHITE, from));
        pieces.add(new Pawn(WHITE, between));
        board = new Board(pieces);

        assertThatThrownBy(() -> board.movePiece(from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenThereIsAPieceOnTheWayToStraightDestinationSquare() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.E, 8);
        Square between = new Square(Letter.E, 6);
        pieces = new ArrayList<>();
        pieces.add(new Rook(WHITE, from));
        pieces.add(new Pawn(WHITE, between));
        board = new Board(pieces);

        assertThatThrownBy(() -> board.movePiece(from, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void knightMovesEvenIfThereIsAPieceOnTheWay() {
        Square from = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 6);
        Square between = new Square(Letter.E, 5);
        pieces = new ArrayList<>();
        pieces.add(new Knight(WHITE, from));
        pieces.add(new Pawn(WHITE, between));
        board = new Board(pieces);

        board.movePiece(from, destination);

        Piece knight = getPieceFromSquare(destination);

        assertThat(knight).isNotNull();
    }

    @Test
    public void throwInvalidMoveExceptionWhenKingTriesToMoveToAnAttackedSquare() {

    }

    //TODO: castling
    //TODO: En passant
    //TODO: Pawn Promotion
    //TODO: Check
    //TODO: Check Mate
    //TODO: Stalemate
    //TODO: Draw
    //TODO: Piece can't move if king is or will be in check

    private Piece getPieceFromSquare(Square square) {
        return board.getPieces()
                    .stream()
                    .filter(p -> p != null)
                    .filter(p -> p.getSquare().equals(square))
                    .findFirst()
                    .orElse(null);
    }
}