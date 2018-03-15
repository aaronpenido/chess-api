package com.chessmasters.chessapi.exceptions;

public class GameStartedException extends RuntimeException {

    public GameStartedException(Long id) {
        super(String.format("Game is already started. Id: %s", id));
    }
}
