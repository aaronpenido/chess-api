package com.chessmasters.chessapi.exceptions;

public class GameNotStartedException extends RuntimeException {

    public GameNotStartedException(Long id) {
        super(String.format("Game not started. Id: %s", id));
    }

}
