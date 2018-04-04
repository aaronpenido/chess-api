package com.chessmasters.chessapi.enums;

public enum ErrorMessage {

    INVALID_MOVE_ITS_OPPONENTS_TURN("Invalid move. It's opponent's turn"),
    INVALID_MOVE_ITS_OPPONENTS_PIECE("Invalid move. It's opponent's piece"),
    CREATE_GAME_ERROR("An error occurred when trying to create a game."),
    CREATE_PLAYER_ERROR("An error occurred when trying to create a player.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
