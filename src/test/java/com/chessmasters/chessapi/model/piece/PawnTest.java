package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    public void whiteMovesTwoSquaresAheadOnlyInRankNumberSeven() {
        final int rankNumberTwo = 2;
        final int nextSquare = rankNumberTwo + 1;
        final int twoSquaresAhead = nextSquare + 1;
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.A, rankNumberTwo);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates).contains(
                new Coordinate(Coordinate.LetterFile.A, nextSquare),
                new Coordinate(Coordinate.LetterFile.A, twoSquaresAhead));
    }

    @Test
    public void validWhiteMovesInLeftBorderAreOneNumberAheadAndOneSquareInRightDiagonal() {
        final int randomNumberFromThreeToSeven = new Random().nextInt(4) + 3;
        final int nextSquare = randomNumberFromThreeToSeven + 1;
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.A, randomNumberFromThreeToSeven);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates).containsExactlyInAnyOrder(
                new Coordinate(Coordinate.LetterFile.A, nextSquare),
                new Coordinate(Coordinate.LetterFile.B, nextSquare));
    }

    @Test
    public void validWhiteMovesInCenterAreOneNumberAheadAndOneSquareInBothDiagonals() {
        final int randomNumberFromThreeToSeven = new Random().nextInt(4) + 3;
        final int nextSquare = randomNumberFromThreeToSeven + 1;
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.E, randomNumberFromThreeToSeven);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates).containsExactlyInAnyOrder(
                new Coordinate(Coordinate.LetterFile.E, nextSquare),
                new Coordinate(Coordinate.LetterFile.D, nextSquare),
                new Coordinate(Coordinate.LetterFile.F, nextSquare));
    }

    @Test
    public void validWhiteMovesInRightBorderAreOneNumberAheadAndOneSquareInLeftDiagonal() {
        final int randomNumberFromThreeToSeven = new Random().nextInt(4) + 3;
        final int nextSquare = randomNumberFromThreeToSeven + 1;
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.H, randomNumberFromThreeToSeven);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates).containsExactlyInAnyOrder(
                new Coordinate(Coordinate.LetterFile.H, nextSquare),
                new Coordinate(Coordinate.LetterFile.G, nextSquare));
    }

    @Test
    public void thereIsNoValidWhiteMoveInTopBorder() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.E, 8);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isEmpty();
    }

    @Test
    public void blackMovesTwoSquaresAheadOnlyInRankNumberSeven() {
        final int rankNumberSeven = 7;
        final int nextSquare = rankNumberSeven - 1;
        final int twoSquaresAhead = nextSquare - 1;
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.A, rankNumberSeven);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates).contains(
                new Coordinate(Coordinate.LetterFile.A, nextSquare),
                new Coordinate(Coordinate.LetterFile.A, twoSquaresAhead));
    }

    @Test
    public void validBlackMovesInRightBorderAreOneNumberAheadAndOneSquareInLeftDiagonal() {
        final int randomNumberFromSixToTwo = new Random().nextInt(4) + 2;
        final int nextSquare = randomNumberFromSixToTwo - 1;
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.A, randomNumberFromSixToTwo);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates).containsExactlyInAnyOrder(
                new Coordinate(Coordinate.LetterFile.A, nextSquare),
                new Coordinate(Coordinate.LetterFile.B, nextSquare));
    }

    @Test
    public void validBlackMovesInCenterAreOneNumberAheadAndOneSquareInBothDiagonals() {
        final int randomNumberFromSixToTwo = new Random().nextInt(4) + 2;
        final int nextSquare = randomNumberFromSixToTwo - 1;
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.E, randomNumberFromSixToTwo);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates).containsExactlyInAnyOrder(
                new Coordinate(Coordinate.LetterFile.E, nextSquare),
                new Coordinate(Coordinate.LetterFile.D, nextSquare),
                new Coordinate(Coordinate.LetterFile.F, nextSquare));
    }

    @Test
    public void validBlackMovesInLeftBorderAreOneNumberAheadAndOneSquareInRightDiagonal() {
        final int randomNumberFromSixToTwo = new Random().nextInt(4) + 2;
        final int nextSquare = randomNumberFromSixToTwo - 1;
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.H, randomNumberFromSixToTwo);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isNotNull();
        assertThat(validCoordinates).containsExactlyInAnyOrder(
                new Coordinate(Coordinate.LetterFile.H, nextSquare),
                new Coordinate(Coordinate.LetterFile.G, nextSquare));
    }

    @Test
    public void thereIsNoValidBlackMoveInTopBorder() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Coordinate coordinate = new Coordinate(Coordinate.LetterFile.E, 1);

        List<Coordinate> validCoordinates = pawn.getValidCoordinates(coordinate);

        assertThat(validCoordinates).isEmpty();
    }
}