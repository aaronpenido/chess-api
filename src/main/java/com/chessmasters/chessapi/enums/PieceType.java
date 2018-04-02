package com.chessmasters.chessapi.enums;

public enum PieceType {
    KING("King"), QUEEN("Queen"), ROOK("Rook"), BISHOP("Bishop"), KNIGHT("Knight"), PAWN("Pawn");

    private String name;

    PieceType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
