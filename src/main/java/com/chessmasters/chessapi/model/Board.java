package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.piece.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.chessmasters.chessapi.enums.PieceColor.*;
import static com.chessmasters.chessapi.model.Coordinate.*;

public class Board {
    private List<Piece> pieces;
    private List<Square> squares;

    public Board() {
        initializeSquares();
        initializePieces();
        fillBoard();
    }

    public List<Square> getSquares() {
        return squares;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    private void initializeSquares() {
        squares = new ArrayList<>();

        for (LetterFile letter: LetterFile.values()) {
            for (int i = 1; i <= 8; i++) {
                Coordinate coordinate = new Coordinate(letter, i);
                squares.add(new Square(coordinate));
            }
        }
    }

    private void initializePieces() {
        pieces = new ArrayList<>();

        pieces.add(new King(WHITE));
        pieces.add(new King(BLACK));
        pieces.add(new Queen(WHITE));
        pieces.add(new Queen(BLACK));

        for(int i = 0; i < 2; i++) {
            pieces.add(new Rook(WHITE));
            pieces.add(new Rook(BLACK));
            pieces.add(new Bishop(WHITE));
            pieces.add(new Bishop(BLACK));
            pieces.add(new Knight(WHITE));
            pieces.add(new Knight(BLACK));
        }

        for(int i = 0; i < 8; i++) {
            pieces.add(new Pawn(WHITE));
            pieces.add(new Pawn(BLACK));
        }
    }

    private void fillBoard() {
        fillKings();
        fillQueens();
        fillRooks();
        fillBishops();
        fillKnights();
        fillPawns();
    }

    private void fillKings() {
        fillPiece(King.class, PieceColor.WHITE, new Coordinate(LetterFile.E, 1));
        fillPiece(King.class, PieceColor.BLACK, new Coordinate(LetterFile.E, 8));
    }

    private void fillQueens() {
        fillPiece(Queen.class, PieceColor.WHITE, new Coordinate(LetterFile.D, 1));
        fillPiece(Queen.class, PieceColor.BLACK, new Coordinate(LetterFile.D, 8));
    }

    private void fillRooks() {
        fillPieces(Rook.class,
                PieceColor.WHITE,
                Arrays.asList(
                    new Coordinate(LetterFile.A, 1),
                    new Coordinate(LetterFile.H, 1)));

        fillPieces(Rook.class,
                PieceColor.BLACK,
                Arrays.asList(
                        new Coordinate(LetterFile.A, 8),
                        new Coordinate(LetterFile.H, 8)));
    }

    private void fillBishops() {
        fillPieces(Bishop.class,
                PieceColor.WHITE,
                Arrays.asList(
                    new Coordinate(LetterFile.C, 1),
                    new Coordinate(LetterFile.F, 1)));

        fillPieces(Bishop.class,
                PieceColor.BLACK,
                Arrays.asList(
                        new Coordinate(LetterFile.C, 8),
                        new Coordinate(LetterFile.F, 8)));
    }

    private void fillKnights() {
        fillPieces(Knight.class,
                PieceColor.WHITE,
                Arrays.asList(
                    new Coordinate(LetterFile.B, 1),
                    new Coordinate(LetterFile.G, 1)));

        fillPieces(Knight.class,
                PieceColor.BLACK,
                Arrays.asList(
                        new Coordinate(LetterFile.B, 8),
                        new Coordinate(LetterFile.G, 8)));
    }

    private void fillPawns() {
        fillPawn(PieceColor.WHITE, 2);
        fillPawn(PieceColor.BLACK, 7);
    }

    private void fillPawn(PieceColor color, int number) {
        for (int i = 1; i < LetterFile.values().length; i++) {
            LetterFile letter = LetterFile.values()[i];
            Coordinate coordinate = new Coordinate(letter, number);
            fillPiece(Pawn.class, color, coordinate);
        }
    }

    private void fillPiece(Class pieceClass, PieceColor color, Coordinate coordinate) {
        Piece piece = pieces
                .stream()
                .filter(p -> p.getColor().equals(color))
                .filter(pieceClass::isInstance)
                .findFirst()
                .orElse(null);

        Square square = getSquare(coordinate);
        square.fill(piece);
    }

    private void fillPieces(Class pieceClass, PieceColor color, List<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            fillPiece(pieceClass, color, coordinate);
        }
    }

    public Square getSquare(Coordinate coordinate) {
        return squares
                .stream()
                .filter(s -> s.getCoordinate().equals(coordinate))
                .findFirst()
                .orElse(null);
    }
}
