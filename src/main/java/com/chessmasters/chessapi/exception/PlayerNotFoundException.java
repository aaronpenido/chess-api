package com.chessmasters.chessapi.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException() {
        super("Player not found.");
    }
}
