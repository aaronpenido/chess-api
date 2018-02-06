package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.model.piece.*;
import java.util.ArrayList;
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

    private void fillBoardWithWhitePieces() {
        fillWhiteKing();
        fillWhiteQueen();
        fillWhiteRooks();
        fillWhiteBishops();
        fillWhiteKnights();
        fillWhitePawns();
    }

    private void fillWhiteKing() {
        King king = pieces
                .stream()
                .filter(p -> p.getColor().equals(WHITE))
                .filter(King.class::isInstance)
                .map(King.class::cast)
                .findFirst()
                .orElse(null);

        Square square = getSquare(new Coordinate(LetterFile.E, 1));
        square.fill(king);
    }

    private void fillWhiteQueen() {
         Queen queen = pieces
                .stream()
                 .filter(p -> p.getColor().equals(WHITE))
                .filter(Queen.class::isInstance)
                .map(Queen.class::cast)
                .findFirst()
                .orElse(null);

        Square square = getSquare(new Coordinate(LetterFile.D, 1));
        square.fill(queen);
    }

    private void fillWhiteRooks() {
        List<Rook> rooks = pieces
                .stream()
                .filter(p -> p.getColor().equals(WHITE))
                .filter(Rook.class::isInstance)
                .map(Rook.class::cast)
                .collect(Collectors.toList());

        Square square = getSquare(new Coordinate(LetterFile.A, 1));
        square.fill(rooks.get(0));

        square = getSquare(new Coordinate(LetterFile.H, 1));
        square.fill(rooks.get(1));
    }

    private void fillWhiteBishops() {
        List<Bishop> bishops = pieces
                .stream()
                .filter(p -> p.getColor().equals(WHITE))
                .filter(Bishop.class::isInstance)
                .map(Bishop.class::cast)
                .collect(Collectors.toList());

        Square square = getSquare(new Coordinate(LetterFile.C, 1));
        square.fill(bishops.get(0));

        square = getSquare(new Coordinate(LetterFile.F, 1));
        square.fill(bishops.get(1));
    }

    private void fillWhiteKnights() {
        List<Knight> knights = pieces
                .stream()
                .filter(p -> p.getColor().equals(WHITE))
                .filter(Knight.class::isInstance)
                .map(Knight.class::cast)
                .collect(Collectors.toList());

        Square square = getSquare(new Coordinate(LetterFile.B, 1));
        square.fill(knights.get(0));

        square = getSquare(new Coordinate(LetterFile.G, 1));
        square.fill(knights.get(1));
    }

    private void fillWhitePawns() {
        List<Pawn> pawns = pieces
                .stream()
                .filter(p -> p.getColor().equals(WHITE))
                .filter(Pawn.class::isInstance)
                .map(Pawn.class::cast)
                .collect(Collectors.toList());

        for (int i = 1; i < LetterFile.values().length; i++) {
            LetterFile letter = LetterFile.values()[i];
            Square square = getSquare(new Coordinate(letter, 2));
            square.fill(pawns.get(i));
        }
    }

    private void fillBoardWithBlackPieces() {

    }

    public Square getSquare(Coordinate coordinate) {
        return squares
                .stream()
                .filter(s -> s.getCoordinate().equals(coordinate))
                .findFirst()
                .orElse(null);
    }
}
