package com.chessmasters.chessapi.exception;

import com.chessmasters.chessapi.Square;

public class PieceNotFoundException extends RuntimeException {

    public PieceNotFoundException() {
        super("Piece in square was not found");
    }
}
