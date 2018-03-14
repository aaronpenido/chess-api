package com.chessmasters.chessapi.exceptions;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(Long id) {
        super(String.format("Player not found.Id: %s", id));
    }
}
