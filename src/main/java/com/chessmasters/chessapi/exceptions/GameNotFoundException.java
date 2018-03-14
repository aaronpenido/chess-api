package com.chessmasters.chessapi.exceptions;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(Long id) {
        super(String.format("Game not found. Id: %s", id));
    }
}
