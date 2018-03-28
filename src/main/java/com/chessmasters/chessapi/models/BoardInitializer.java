package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PieceEntity;
import com.chessmasters.chessapi.entities.SquareEntity;
import com.chessmasters.chessapi.enums.Letter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;

public class BoardInitializer {

    private GameEntity gameEntity;
    private List<PieceEntity> pieces;
    private static final int WHITE_INITIAL_SQUARE_NUMBER = 1;
    private static final int BLACK_INITIAL_SQUARE_NUMBER = 8;

    public BoardInitializer(GameEntity gameEntity) {
        this.gameEntity = gameEntity;

        initializePieces();
    }

    public List<PieceEntity> getPieces() {
        return pieces;
    }

    private void initializePieces() {
        pieces = new ArrayList<>();

        addQueens();
        addKings();
        addPawns();
        addKnights();
        addRooks();
        addBishops();
    }

    private void addKings() {
        final String pieceType = "King";
        final Letter initialSquareLetter = Letter.E;

        addPieces(pieceType, initialSquareLetter);
    }

    private void addQueens() {
        final String pieceType = "Queen";
        final Letter initialSquareLetter = Letter.D;

        addPieces(pieceType, initialSquareLetter);
    }

    private void addPawns() {
        final String pieceType = "Pawn";
        final int whiteInitialSquareNumber = 2;
        final int blackInitialSquareNumber = 7;

        Arrays.stream(Letter.values()).forEach(letter ->
                addPieces(pieceType, letter, whiteInitialSquareNumber, blackInitialSquareNumber));
    }

    private void addKnights() {
        final String pieceType = "Knight";
        final Letter firstInitialSquareLetter = Letter.B;
        final Letter secondInitialSquareLetter = Letter.G;

        addPieces(pieceType, firstInitialSquareLetter);
        addPieces(pieceType, secondInitialSquareLetter);
    }

    private void addRooks() {
        final String pieceType = "Rook";
        final Letter firstInitialSquareLetter = Letter.A;
        final Letter secondInitialSquareLetter = Letter.H;

        addPieces(pieceType, firstInitialSquareLetter);
        addPieces(pieceType, secondInitialSquareLetter);
    }

    private void addBishops() {
        final String pieceType = "Bishop";
        final Letter firstInitialSquareLetter = Letter.C;
        final Letter secondInitialSquareLetter = Letter.F;

        addPieces(pieceType, firstInitialSquareLetter);
        addPieces(pieceType, secondInitialSquareLetter);
    }

    private void addPieces(final String pieceType, final Letter initialSquareLetter) {
        addPieces(pieceType, initialSquareLetter, WHITE_INITIAL_SQUARE_NUMBER, BLACK_INITIAL_SQUARE_NUMBER);
    }

    private void addPieces(final String pieceType, final Letter initialSquareLetter,
                          final int whiteInitialSquareNumber, final int blackInitialSquareNumber) {
        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, initialSquareLetter), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, initialSquareLetter), pieceType));
    }
}
