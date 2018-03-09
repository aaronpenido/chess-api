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
        when(game.getPieces()).thenReturn(Collections.singletonList(new Pawn(WHITE, origin)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, origin, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void pieceMovesLeavingPreviousSquareEmpty() {
        Square origin = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Pawn(WHITE, origin)));
        board = new Board(game);

        board.movePiece(whitePlayer, origin, destination);

        Piece piece = board.getPieceFromSquare(origin);

        assertThat(piece).isNull();
    }

    @Test
    public void pieceIsRemovedFromBoardWhenCaptured() {
        Square origin = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, origin),
                new Rook(BLACK, destination)));
        board = new Board(game);

        board.movePiece(whitePlayer, origin, destination);

        assertThat(board.getPieces().size()).isEqualTo(1);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPawnTriesToMoveAheadToFilledSquare() {
        Square origin = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        when(game.getPieces()).thenReturn(Arrays.asList(
                new Pawn(WHITE, origin),
                new Pawn(BLACK, destination)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, origin, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPieceTriesToMoveToFilledSquareWithSamePieceColor() {
        Square origin = new Square(Letter.A, 2);
        Square destination = new Square(Letter.A, 3);
        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, origin),
                new Rook(WHITE, destination)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, origin, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenThereIsAPieceOnTheWayToDiagonalDestinationSquare() {
        Square origin = new Square(Letter.E, 4);
        Square destination = new Square(Letter.A, 8);
        Square between = new Square(Letter.C, 6);
        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, origin),
                new Rook(WHITE, between)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, origin, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenThereIsAPieceOnTheWayToStraightDestinationSquare() {
        Square origin = new Square(Letter.E, 4);
        Square destination = new Square(Letter.E, 8);
        Square between = new Square(Letter.E, 6);
        when(game.getPieces()).thenReturn(Arrays.asList(
                new Rook(WHITE, origin),
                new Pawn(WHITE, between)));
        board = new Board(game);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, origin, destination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void knightMovesEvenIfThereIsAPieceOnTheWay() {
        Square origin = new Square(Letter.E, 4);
        Square destination = new Square(Letter.F, 6);
        Square between = new Square(Letter.E, 5);
        when(game.getPieces()).thenReturn(Arrays.asList(
                new Knight(WHITE, origin),
                new Pawn(WHITE, between)));
        board = new Board(game);

        board.movePiece(whitePlayer, origin, destination);

        Piece knight = board.getPieceFromSquare(destination);

        assertThat(knight).isNotNull();
    }

    @Test
    public void throwInvalidMoveExceptionWhenMovePieceAfterAnotherWithSameColor() {
        Square origin = new Square(Letter.E, 4);
        Square destination = new Square(Letter.E, 5);
        Square secondDestination = new Square(Letter.E, 6);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Pawn(WHITE, origin)));
        board = new Board(game);

        board.movePiece(whitePlayer, origin, destination);

        assertThatThrownBy(() -> board.movePiece(whitePlayer, destination, secondDestination))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    public void throwInvalidMoveExceptionWhenPlayerTriesToMoveOpponentsPiece() {
        Square origin = new Square(Letter.E, 2);
        Square destination = new Square(Letter.E, 3);
        when(game.getPieces()).thenReturn(Collections.singletonList(new Pawn(WHITE, origin)));
        board = new Board(game);

        board.movePiece(whitePlayer, origin, destination);

        Square nextMoveFrom = destination;
        Square nextMoveDestination = new Square(Letter.E, 4);

        assertThatThrownBy(() -> board.movePiece(new Player(BLACK), nextMoveFrom, nextMoveDestination))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessageContaining("It's opponent's turn");
    }

    /*
    @Test
    public void movePiece() {
        Square from = new Square(Letter.E, 2);
        Square destination = new Square(Letter.E, 4);

        game.movePiece(new Player(WHITE), from, destination);

        assertThat(game.getPieces()).contains(new Pawn(WHITE, destination));
    }

    @Test
    public void throwExceptionWhenTryingToMovePieceOnNonStartedGame() {
        Square from = new Square(Letter.E, 2);
        Square destination = new Square(Letter.E, 3);
        game = new Game();
        final Long gameId = 1L;
        ReflectionTestUtils.setField(game, "id", gameId);

        assertThatThrownBy(() -> game.movePiece(new Player(WHITE), from, destination))
                .isInstanceOf(GameNotStartedException.class);
    }
     */
}