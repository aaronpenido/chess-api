package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.piece.*;
import org.junit.Ignore;
import org.junit.Test;

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
        Coordinate coordinate = new Coordinate(LetterFile.E, 1);
        King whiteKing = getPieceFromCoordinate(coordinate);

        assertThat(whiteKing).isNotNull();
        assertThat(whiteKing.getColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void whiteQueenIsOnD1Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.D, 1);
        Queen whiteQueen = getPieceFromCoordinate(coordinate);

        assertThat(whiteQueen).isNotNull();
        assertThat(whiteQueen.getColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void whiteRookIsOnA1Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.A, 1);
        Rook whiteRook = getPieceFromCoordinate(coordinate);

        assertThat(whiteRook).isNotNull();
        assertThat(whiteRook.getColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void whiteRookIsOnH1Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.H, 1);
        Rook whiteRook = getPieceFromCoordinate(coordinate);

        assertThat(whiteRook).isNotNull();
        assertThat(whiteRook.getColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void whiteBishopIsOnC1Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.C, 1);
        Bishop whiteBishop = getPieceFromCoordinate(coordinate);

        assertThat(whiteBishop).isNotNull();
        assertThat(whiteBishop.getColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void whiteBishopIsOnF1Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.F, 1);
        Bishop whiteBishop = getPieceFromCoordinate(coordinate);

        assertThat(whiteBishop).isNotNull();
        assertThat(whiteBishop.getColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void whiteKnightIsOnB1Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.B, 1);
        Knight whiteKnight = getPieceFromCoordinate(coordinate);

        assertThat(whiteKnight).isNotNull();
        assertThat(whiteKnight.getColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void whiteKnightIsOnG1Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.G, 1);
        Knight whiteKnight = getPieceFromCoordinate(coordinate);

        assertThat(whiteKnight).isNotNull();
        assertThat(whiteKnight.getColor()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    public void whitePawnsAreOnRightCoordinates() {
        for (int i = 1; i < LetterFile.values().length; i++) {
            LetterFile letterFile = LetterFile.values()[i];
            Coordinate coordinate = new Coordinate(letterFile, 2);
            Pawn pawn = getPieceFromCoordinate(coordinate);

            assertThat(pawn).isNotNull();
            assertThat(pawn.getColor()).isEqualTo(PieceColor.WHITE);
        }
    }

    @Ignore
    @Test
    public void blackRookIsOnA8Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.A, 8);
        Rook blackRook = getPieceFromCoordinate(coordinate);

        assertThat(blackRook).isNotNull();
        assertThat(blackRook.getColor()).isEqualTo(PieceColor.BLACK);
    }

    @Ignore
    @Test
    public void blackRookIsOnH8Coordinate() {
        Coordinate coordinate = new Coordinate(LetterFile.H, 8);
        Rook blackRook   = getPieceFromCoordinate(coordinate);

        assertThat(blackRook).isNotNull();
        assertThat(blackRook.getColor()).isEqualTo(PieceColor.BLACK);
    }

    private <T extends Piece> T getPieceFromCoordinate(Coordinate coordinate) {
        Board board = new Board();

        return (T)board.getSquare(coordinate).getPiece();
    }
}