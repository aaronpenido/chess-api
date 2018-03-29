package com.chessmasters.chessapi.exceptions;

public class PieceNotFoundException extends RuntimeException {
    public PieceNotFoundException(Long id) {
        super(String.format("Piece not found. Id: %s", id));
    }
}
