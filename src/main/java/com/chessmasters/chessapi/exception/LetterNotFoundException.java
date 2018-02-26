package com.chessmasters.chessapi.exception;

public class LetterNotFoundException extends RuntimeException {

    public LetterNotFoundException(String message) {
        super(message);
    }
}
