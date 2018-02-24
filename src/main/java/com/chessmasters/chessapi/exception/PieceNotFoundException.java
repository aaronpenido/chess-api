package com.chessmasters.chessapi.exception;

import com.chessmasters.chessapi.Square;

public class PieceNotFoundException extends RuntimeException {
    public PieceNotFoundException(Square from) {
    }
}
