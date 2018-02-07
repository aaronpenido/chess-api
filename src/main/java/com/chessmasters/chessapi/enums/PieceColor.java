package com.chessmasters.chessapi.enums;

public enum PieceColor {
    BLACK ("Black"),
    WHITE ("White");

    private final String name;

    PieceColor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
