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

    public static Color opposite(Color color) {
        return color.equals(WHITE) ? BLACK : WHITE;
    }
}
