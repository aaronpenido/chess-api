package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    public void validMoveInLeftBorder() {
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
    public void validMoveInCenter() {
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
    public void validMoveInRightBorder() {
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
}