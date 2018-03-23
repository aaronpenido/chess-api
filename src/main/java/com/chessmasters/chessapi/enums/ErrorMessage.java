package com.chessmasters.chessapi.enums;

public enum ErrorMessage {

    INVALID_MOVE_ITS_OPPONENTS_TURN("Invalid move. It's opponent's turn");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
