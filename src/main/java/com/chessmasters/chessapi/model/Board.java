package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.piece.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        fillBoardWithWhitePieces();
        fillBoardWithBlackPieces();
    }

    private void fillBoardWithBlackPieces() {
        fillBlackKing();
        fillBlackQueen();
        fillBlackRooks();
        fillBlackBishops();
        fillBlackKnights();
        fillBlackPawns();
    }

    private void fillBoardWithWhitePieces() {
        fillWhiteKing();
        fillWhiteQueen();
        fillWhiteRooks();
        fillWhiteBishops();
        fillWhiteKnights();
        fillWhitePawns();
    }

    private void fillWhiteKing() {
        int number = 1;
        Coordinate coordinate = new Coordinate(LetterFile.E, number);
        fillKing(PieceColor.WHITE, coordinate);
    }

    private void fillBlackKing() {
        int number = 8;
        Coordinate coordinate = new Coordinate(LetterFile.E, number);
        fillKing(PieceColor.BLACK, coordinate);
    }

    private void fillKing(PieceColor color, Coordinate coordinate) {
        King king = pieces
                .stream()
                .filter(p -> p.getColor().equals(color))
                .filter(King.class::isInstance)
                .map(King.class::cast)
                .findFirst()
                .orElse(null);

        Square square = getSquare(coordinate);
        square.fill(king);
    }

    private void fillWhiteQueen() {
        int number = 1;
        Coordinate coordinate = new Coordinate(LetterFile.D, number);
        fillQueen(PieceColor.WHITE, coordinate);
    }

    private void fillBlackQueen() {
        int number = 8;
        Coordinate coordinate = new Coordinate(LetterFile.D, number);
        fillQueen(PieceColor.BLACK, coordinate);
    }

    private void fillQueen(PieceColor color, Coordinate coordinate) {
        Queen queen = pieces
                .stream()
                .filter(p -> p.getColor().equals(color))
                .filter(Queen.class::isInstance)
                .map(Queen.class::cast)
                .findFirst()
                .orElse(null);

        Square square = getSquare(coordinate);
        square.fill(queen);
    }

    private void fillWhiteRooks() {
        final int number = 1;

        fillRooks(PieceColor.WHITE, Arrays.asList(
                new Coordinate(LetterFile.A, number),
                new Coordinate(LetterFile.H, number)));
    }

    private void fillBlackRooks() {
        final int number = 8;

        fillRooks(PieceColor.BLACK, Arrays.asList(
                new Coordinate(LetterFile.A, number),
                new Coordinate(LetterFile.H, number)));
    }

    private void fillRooks(PieceColor color, List<Coordinate> coordinates) {
        List<Rook> rooks = pieces
                .stream()
                .filter(p -> p.getColor().equals(color))
                .filter(Rook.class::isInstance)
                .map(Rook.class::cast)
                .collect(Collectors.toList());

        Square square = getSquare(coordinates.get(0));
        square.fill(rooks.get(0));

        square = getSquare(coordinates.get(1));
        square.fill(rooks.get(1));
    }

    private void fillWhiteBishops() {
        final int number = 1;

        fillBishops(PieceColor.WHITE, Arrays.asList(
                new Coordinate(LetterFile.C, number),
                new Coordinate(LetterFile.F, number)));
    }

    private void fillBlackBishops() {
        final int number = 8;

        fillBishops(PieceColor.BLACK, Arrays.asList(
                new Coordinate(LetterFile.C, number),
                new Coordinate(LetterFile.F, number)));
    }

    private void fillBishops(PieceColor color, List<Coordinate> coordinates) {
        List<Bishop> bishops = pieces
                .stream()
                .filter(p -> p.getColor().equals(color))
                .filter(Bishop.class::isInstance)
                .map(Bishop.class::cast)
                .collect(Collectors.toList());

        Square square = getSquare(coordinates.get(0));
        square.fill(bishops.get(0));

        square = getSquare(coordinates.get(1));
        square.fill(bishops.get(1));
    }

    private void fillWhiteKnights() {
        final int number = 1;

        fillKnights(PieceColor.WHITE, Arrays.asList(
                new Coordinate(LetterFile.B, number),
                new Coordinate(LetterFile.G, number)));
    }

    private void fillBlackKnights() {
        final int number = 8;

        fillKnights(PieceColor.BLACK, Arrays.asList(
                new Coordinate(LetterFile.B, number),
                new Coordinate(LetterFile.G, number)));
    }

    private void fillKnights(PieceColor color, List<Coordinate> coordinates) {
        List<Knight> knights = pieces
                .stream()
                .filter(p -> p.getColor().equals(color))
                .filter(Knight.class::isInstance)
                .map(Knight.class::cast)
                .collect(Collectors.toList());

        Square square = getSquare(coordinates.get(0));
        square.fill(knights.get(0));

        square = getSquare(coordinates.get(1));
        square.fill(knights.get(1));
    }

    private void fillWhitePawns() {
        fillPawns(PieceColor.WHITE, 2);
    }

    private void fillBlackPawns() {
        fillPawns(PieceColor.BLACK, 7);
    }

    private void fillPawns(PieceColor color, int number) {
        List<Pawn> pawns = pieces
                .stream()
                .filter(p -> p.getColor().equals(color))
                .filter(Pawn.class::isInstance)
                .map(Pawn.class::cast)
                .collect(Collectors.toList());

        for (int i = 1; i < LetterFile.values().length; i++) {
            LetterFile letter = LetterFile.values()[i];
            Square square = getSquare(new Coordinate(letter, number));
            square.fill(pawns.get(i));
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
