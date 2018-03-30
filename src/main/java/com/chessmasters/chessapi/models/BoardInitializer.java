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
    }

    public void initialize() {
        pieces = new ArrayList<>();

        addKings();
        addQueens();
        addBishops();
        addKnights();
        addRooks();
        addPawns();

        gameEntity.getPieces().addAll(pieces);
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

    private void addBishops() {
        final String pieceType = "Bishop";
        final Letter firstInitialSquareLetter = Letter.C;
        final Letter secondInitialSquareLetter = Letter.F;

        addPieces(pieceType, firstInitialSquareLetter);
        addPieces(pieceType, secondInitialSquareLetter);
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

    private void addPawns() {
        final String pieceType = "Pawn";
        final int whiteInitialSquareNumber = 2;
        final int blackInitialSquareNumber = 7;

        Arrays.stream(Letter.values()).forEach(letter ->
                addPieces(pieceType,
                        new SquareEntity(whiteInitialSquareNumber, letter),
                        new SquareEntity(blackInitialSquareNumber, letter)
                )
        );
    }

    private void addPieces(final String pieceType, final Letter initialSquareLetter) {
        final SquareEntity whiteSquare = new SquareEntity(WHITE_INITIAL_SQUARE_NUMBER, initialSquareLetter);
        final SquareEntity blackSquare = new SquareEntity(BLACK_INITIAL_SQUARE_NUMBER, initialSquareLetter);

        addPieces(pieceType, whiteSquare, blackSquare);
    }

    private void addPieces(final String pieceType, final SquareEntity whiteSquare, final SquareEntity blackSquare) {
        final PieceEntity whitePiece = new PieceEntity(gameEntity, WHITE, whiteSquare, pieceType);
        final PieceEntity blackPiece = new PieceEntity(gameEntity, BLACK, blackSquare, pieceType);

        pieces.add(whitePiece);
        pieces.add(blackPiece);
    }
}
