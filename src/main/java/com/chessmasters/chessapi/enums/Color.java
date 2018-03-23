package com.chessmasters.chessapi.enums;

public enum Color {
    BLACK("Black"), WHITE("White");

    private String color;

    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

    public Color opposite() {
        return this.equals(WHITE) ? BLACK : WHITE;
    }
}
