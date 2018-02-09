package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.piece.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static com.chessmasters.chessapi.model.Coordinate.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void boardHasAllSquares() {
        final int NUMBER_OF_SQUARES = 64;

        assertThat(board.getSquares()).isNotNull();
        assertThat(board.getSquares().size()).isEqualTo(NUMBER_OF_SQUARES);
    }

    @Test
    public void boardHasThirtyTwoSquaresFilledWithPieces() {
        final int NUMBER_OF_SQUARES_FILLED = 32;

        assertThat(board.getSquares()).isNotNull();
        assertThat(board.getSquares()
                .stream()
                .filter(s -> s.getPiece() != null)
                .collect(Collectors.toList())
                .size()).isEqualTo(NUMBER_OF_SQUARES_FILLED);
    }

    @Test
    public void whiteKingIsOnE1Coordinate() {
        final int number = 1;
        Coordinate coordinate = new Coordinate(LetterFile.E, number);

        assertThatPieceTypeAndCoordinateAreRight(King.class, coordinate, PieceColor.WHITE);
    }

    @Test
    public void whiteQueenIsOnD1Coordinate() {
        final int number = 1;
        Coordinate coordinate = new Coordinate(LetterFile.D, number);

        assertThatPieceTypeAndCoordinateAreRight(Queen.class, coordinate, PieceColor.WHITE);
    }

    @Test
    public void blackKingIsOnE8Coordinate() {
        final int number = 8;
        Coordinate coordinate = new Coordinate(LetterFile.E, number);

        assertThatPieceTypeAndCoordinateAreRight(King.class, coordinate, PieceColor.BLACK);
    }

    @Test
    public void blackQueenIsOnD8Coordinate() {
        final int number = 8;
        Coordinate coordinate = new Coordinate(LetterFile.D, number);
        assertThatPieceTypeAndCoordinateAreRight(Queen.class, coordinate, PieceColor.BLACK);
    }

    @Test
    public void whiteRooksAreOnA1AndH1Coordinates() {
        int number = 1;
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(LetterFile.A, number),
                new Coordinate(LetterFile.H, number));

        for (Coordinate coordinate : coordinates) {
            assertThatPieceTypeAndCoordinateAreRight(Rook.class, coordinate, PieceColor.WHITE);
        }
    }

    @Test
    public void blackRooksAreOnA8AndH8Coordinates() {
        int number = 8;
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(LetterFile.A, number),
                new Coordinate(LetterFile.H, number));

        for (Coordinate coordinate : coordinates) {
            assertThatPieceTypeAndCoordinateAreRight(Rook.class, coordinate, PieceColor.BLACK);
        }
    }

    @Test
    public void whiteBishopsAreOnC1AndF1Coordinates() {
        int number = 1;
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(LetterFile.C, number),
                new Coordinate(LetterFile.F, number));

        for (Coordinate coordinate : coordinates) {
            assertThatPieceTypeAndCoordinateAreRight(Bishop.class, coordinate, PieceColor.WHITE);
        }
    }

    @Test
    public void blackBishopsAreOnC8AndF8Coordinates() {
        int number = 8;
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(LetterFile.C, number),
                new Coordinate(LetterFile.F, number));

        for (Coordinate coordinate : coordinates) {
            assertThatPieceTypeAndCoordinateAreRight(Bishop.class, coordinate, PieceColor.BLACK);
        }
    }

    @Test
    public void whiteKnightsAreOnB1AndG1Coordinate() {
        int number = 1;
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(LetterFile.B, number),
                new Coordinate(LetterFile.G, number));

        for (Coordinate coordinate : coordinates) {
            assertThatPieceTypeAndCoordinateAreRight(Knight.class, coordinate, PieceColor.WHITE);
        }
    }

    @Test
    public void blackKnightsAreOnB8AndG8Coordinate() {
        int number = 8;
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(LetterFile.B, number),
                new Coordinate(LetterFile.G, number));

        for (Coordinate coordinate : coordinates) {
            assertThatPieceTypeAndCoordinateAreRight(Knight.class, coordinate, PieceColor.BLACK);
        }
    }

    @Test
    public void whitePawnsAreOnRightCoordinates() {
        for (int i = 1; i < LetterFile.values().length; i++) {
            LetterFile letterFile = LetterFile.values()[i];
            int number = 2;
            Coordinate coordinate = new Coordinate(letterFile, number);
            assertThatPieceTypeAndCoordinateAreRight(Pawn.class, coordinate, PieceColor.WHITE);
        }
    }

    @Test
    public void blackPawnsAreOnRightCoordinates() {
        for (int i = 1; i < LetterFile.values().length; i++) {
            LetterFile letterFile = LetterFile.values()[i];
            int number = 7;
            Coordinate coordinate = new Coordinate(letterFile, number);
            assertThatPieceTypeAndCoordinateAreRight(Pawn.class, coordinate, PieceColor.BLACK);
        }
    }

    @Test
    public void nextMoveColorIsAlternated() {
        Coordinate origin = new Coordinate(LetterFile.A, 2);
        Coordinate destination = new Coordinate(LetterFile.A, 3);

        board.fillSquare(origin, destination);

        assertThat(board.getNextMoveColor()).isEqualTo(PieceColor.BLACK);

        origin = new Coordinate(LetterFile.A, 7);
        destination = new Coordinate(LetterFile.A, 6);

        board.fillSquare(origin, destination);

        assertThat(board.getNextMoveColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void movedPieceIsInRightSquareAnOldSquareIsEmpty() {
        Coordinate oldCoordinate = new Coordinate(LetterFile.A, 2);
        Pawn pawn = (Pawn)board.getPieceFromCoordinate(oldCoordinate);
        List<Coordinate> pawnCoordinates = pawn.getValidCoordinates(oldCoordinate);
        Coordinate nextCoordinate = pawnCoordinates.get(0);

        board.fillSquare(oldCoordinate, nextCoordinate);

        assertThat(board.getPieceFromCoordinate(oldCoordinate)).isNull();
        assertThat(board.getPieceFromCoordinate(nextCoordinate)).isEqualTo(pawn);
    }

    @Test
    public void invalidMoveDoesntChangePieceSquare() {
        Coordinate oldCoordinate = new Coordinate(LetterFile.A, 2);
        Pawn pawn = (Pawn)board.getPieceFromCoordinate(oldCoordinate);
        Coordinate invalidCoordinate = new Coordinate(LetterFile.A, 5);

        board.fillSquare(oldCoordinate, invalidCoordinate);

        assertThat(board.getPieceFromCoordinate(oldCoordinate)).isEqualTo(pawn);
        assertThat(board.getPieceFromCoordinate(invalidCoordinate)).isNotEqualTo(pawn);
    }

    private void assertThatPieceTypeAndCoordinateAreRight(
            Class pieceClass,
            Coordinate coordinate,
            PieceColor color) {

        Piece piece = board.getPieceFromCoordinate(coordinate);

        assertThat(piece).isNotNull();
        assertThat(piece.getClass()).isEqualTo(pieceClass);
        assertThat(piece.getColor()).isEqualTo(color);
    }
}