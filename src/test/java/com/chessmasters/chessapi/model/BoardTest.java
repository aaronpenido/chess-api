package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.piece.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.chessmasters.chessapi.model.Coordinate.*;

public class BoardTest {

    @Test
    public void boardHasAllPieces() {
        final int NUMBER_OF_PIECES = 32;
        Board board = new Board();

        assertThat(board.getPieces()).isNotNull();
        assertThat(board.getPieces().size()).isEqualTo(NUMBER_OF_PIECES);
    }

    @Test
    public void boardHasAllSquares() {
        final int NUMBER_OF_SQUARES = 64;
        Board board = new Board();

        assertThat(board.getSquares()).isNotNull();
        assertThat(board.getSquares().size()).isEqualTo(NUMBER_OF_SQUARES);
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

    private void assertThatPieceTypeAndCoordinateAreRight(Class pieceClass,
                                                          Coordinate coordinate,
                                                          PieceColor color) {
        Piece piece = getPieceFromCoordinate(coordinate);

        assertThat(piece).isNotNull();
        assertThat(piece.getClass()).isEqualTo(pieceClass);
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private Piece getPieceFromCoordinate(Coordinate coordinate) {
        Board board = new Board();

        return board.getSquare(coordinate).getPiece();
    }
}