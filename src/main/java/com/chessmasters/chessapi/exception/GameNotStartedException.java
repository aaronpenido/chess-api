package com.chessmasters.chessapi.exception;

public class GameNotStartedException extends RuntimeException {

    public GameNotStartedException(long id) {
        super(String.format("The game have not been started. ID: %s", id));
    }
}
