package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    public void validWhiteMoveInLeftBorder() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.A, 2);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates.size()).isEqualTo(3);
        assertThat(validCoordinates).contains(
                new Coordinate(Coordinate.LetterFile.A, 3),
                new Coordinate(Coordinate.LetterFile.A, 4),
                new Coordinate(Coordinate.LetterFile.B, 3));
    }

    @Test
    public void validWhiteMoveInCenter() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.E, 2);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates.size()).isEqualTo(4);
        assertThat(validCoordinates).contains(
                new Coordinate(Coordinate.LetterFile.E, 3),
                new Coordinate(Coordinate.LetterFile.E, 4),
                new Coordinate(Coordinate.LetterFile.D, 3),
                new Coordinate(Coordinate.LetterFile.F, 3));
    }

    @Test
    public void validWhiteMoveInRightBorder() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.H, 2);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates.size()).isEqualTo(3);
        assertThat(validCoordinates).contains(
                new Coordinate(Coordinate.LetterFile.H, 3),
                new Coordinate(Coordinate.LetterFile.H, 4),
                new Coordinate(Coordinate.LetterFile.G, 3));
    }

    @Test
    public void thereIsNoValidWhiteMoveInTopBorder() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.E, 8);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNull();
    }

    @Test
    public void validBlackMoveInRightBorder() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.A, 7);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates.size()).isEqualTo(3);
        assertThat(validCoordinates).contains(
                new Coordinate(Coordinate.LetterFile.A, 6),
                new Coordinate(Coordinate.LetterFile.A, 5),
                new Coordinate(Coordinate.LetterFile.B, 6));
    }

    @Test
    public void validBlackMoveInCenter() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.E, 7);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates.size()).isEqualTo(4);
        assertThat(validCoordinates).contains(
                new Coordinate(Coordinate.LetterFile.E, 6),
                new Coordinate(Coordinate.LetterFile.E, 5),
                new Coordinate(Coordinate.LetterFile.D, 6),
                new Coordinate(Coordinate.LetterFile.F, 6));
    }

    @Test
    public void validBlackMoveInLeftBorder() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.H, 7);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates.size()).isEqualTo(3);
        assertThat(validCoordinates).contains(
                new Coordinate(Coordinate.LetterFile.H, 6),
                new Coordinate(Coordinate.LetterFile.H, 5),
                new Coordinate(Coordinate.LetterFile.G, 6));
    }

    @Test
    public void thereIsNoValidBlackMoveInTopBorder() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.E, 1);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNull();
    }
}