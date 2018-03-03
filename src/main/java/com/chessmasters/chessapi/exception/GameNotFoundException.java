package com.chessmasters.chessapi.exception;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException() {
        super("Game not found.");
    }
}
