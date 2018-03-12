package com.chessmasters.chessapi;

import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.exception.InvalidMoveException;
import com.chessmasters.chessapi.piece.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.Collections;

import static com.chessmasters.chessapi.enums.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    private Board board;
    private Player whitePlayer = new Player(WHITE);
    @Mock
    private Game game;
    
    @Test
    public void throwInvalidMoveExceptionWhenPawnMovesToDiagonalSquareWithoutPieceToCapture() {
        Square origin = new Square(Letter.A, 2);
        Square destination = new Square(Letter.B, 3);
        Pawn pawn = new Pawn(WHITE, origin);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, new Move(pawn, destination)))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void pieceMovesLeavingPreviousSquareEmpty() {
        Square origin = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        Pawn pawn = new Pawn(WHITE, origin);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);

        board.movePiece(whitePlayer, new Move(pawn, destination));

        Piece piece = board.getPieceFromSquare(origin);

        assertThat(piece).isNull();
    }

    @Test
    public void pieceIsRemovedFromBoardWhenCaptured() {
        Rook rook = new Rook(WHITE, new Square(Letter.A, 2));
        Square destination = new Square(Letter.A, 3);
        when(game.getPieces()).thenReturn(Arrays.asList(rook, new Rook(BLACK, destination)));
        board = new Board(game);

        board.movePiece(whitePlayer, new Move(rook, destination));

        assertThat(board.getPieces().size()).isEqualTo(1);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPawnTriesToMoveAheadToFilledSquare() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.A, 2));
        Square destination = new Square(Letter.A, 3);
        when(game.getPieces()).thenReturn(Arrays.asList(pawn, new Pawn(BLACK, destination)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, new Move(pawn, destination)))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPieceTriesToMoveToFilledSquareWithSamePieceColor() {
        Rook rook = new Rook(WHITE, new Square(Letter.A, 2));
        Square destination = new Square(Letter.A, 3);
        when(game.getPieces()).thenReturn(Arrays.asList(rook, new Rook(WHITE, destination)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, new Move(rook, destination)))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenThereIsAPieceOnTheWayToDiagonalDestinationSquare() {
        Rook rook = new Rook(WHITE, new Square(Letter.E, 4));
        Square destination = new Square(Letter.A, 8);
        Square between = new Square(Letter.C, 6);
        when(game.getPieces()).thenReturn(Arrays.asList(rook, new Rook(WHITE, between)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, new Move(rook, destination)))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenThereIsAPieceOnTheWayToStraightDestinationSquare() {
        Rook rook = new Rook(WHITE, new Square(Letter.E, 4));
        Square destination = new Square(Letter.E, 8);
        Square between = new Square(Letter.E, 6);
        when(game.getPieces()).thenReturn(Arrays.asList(rook, new Pawn(WHITE, between)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, new Move(rook, destination)))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void knightMovesEvenIfThereIsAPieceOnTheWay() {
        Knight knight = new Knight(WHITE, new Square(Letter.E, 4));
        Square destination = new Square(Letter.F, 6);
        Square between = new Square(Letter.E, 5);
        when(game.getPieces()).thenReturn(Arrays.asList(knight, new Pawn(WHITE, between)));
        board = new Board(game);

        board.movePiece(whitePlayer, new Move(knight, destination));

        assertThat(knight.getSquare()).isEqualTo(destination);
    }

    @Test
    public void throwInvalidMoveExceptionWhenMovePieceAfterAnotherWithSameColor() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.E, 4));
        Square destination = new Square(Letter.E, 5);
        Square secondDestination = new Square(Letter.E, 6);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);

        board.movePiece(whitePlayer, new Move(pawn, destination));

        assertThatThrownBy(() -> board.movePiece(whitePlayer, new Move(pawn, secondDestination)))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPlayerTriesToMoveOpponentsPiece() {
        Pawn pawn = new Pawn(WHITE, new Square(Letter.E, 2));
        Square destination = new Square(Letter.E, 3);
        when(game.getPieces()).thenReturn(Collections.singletonList(pawn));
        board = new Board(game);

        board.movePiece(whitePlayer, new Move(pawn, destination));

        Square nextMoveFrom = destination;
        Square nextMoveDestination = new Square(Letter.E, 4);

        assertThatThrownBy(() -> board.movePiece(new Player(BLACK), new Move(pawn, nextMoveDestination)))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessageContaining("It's opponent's turn");
    }
}