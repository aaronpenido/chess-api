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
        final int whiteInitialSquareNumber = 1;
        final int blackInitialSquareNumber = 8;

        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, Letter.E), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, Letter.E), pieceType));
    }

    private void addQueens() {
        final String pieceType = "Queen";
        final int whiteInitialSquareNumber = 1;
        final int blackInitialSquareNumber = 8;

        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, Letter.D), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, Letter.D), pieceType));
    }

    private void addPawns() {
        final String pieceType = "Pawn";
        final int whiteInitialSquareNumber = 2;
        final int blackInitialSquareNumber = 7;

        Arrays.stream(Letter.values())
                .map(letter -> new SquareEntity(whiteInitialSquareNumber, letter))
                .map(square -> new PieceEntity(gameEntity, WHITE, square, pieceType))
                .forEach(pieces::add);

        Arrays.stream(Letter.values())
                .map(letter -> new SquareEntity(blackInitialSquareNumber, letter))
                .map(square -> new PieceEntity(gameEntity, BLACK, square, pieceType))
                .forEach(pieces::add);
    }

    private void addKnights() {
        final String pieceType = "Knight";
        final int whiteInitialSquareNumber = 1;
        final int blackInitialSquareNumber = 8;

        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, Letter.B), pieceType));
        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, Letter.G), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, Letter.B), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, Letter.G), pieceType));
    }

    private void addRooks() {
        final String pieceType = "Rook";
        final int whiteInitialSquareNumber = 1;
        final int blackInitialSquareNumber = 8;

        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, Letter.A), pieceType));
        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, Letter.H), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, Letter.A), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, Letter.H), pieceType));
    }

    private void addBishops() {
        final String pieceType = "Bishop";
        final int whiteInitialSquareNumber = 1;
        final int blackInitialSquareNumber = 8;

        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, Letter.C), pieceType));
        pieces.add(new PieceEntity(gameEntity, WHITE, new SquareEntity(whiteInitialSquareNumber, Letter.F), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, Letter.C), pieceType));
        pieces.add(new PieceEntity(gameEntity, BLACK, new SquareEntity(blackInitialSquareNumber, Letter.F), pieceType));
    }
}
