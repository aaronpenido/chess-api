package com.chessmasters.chessapi.exception;

import com.chessmasters.chessapi.entity.Square;

public class PieceNotFoundException extends RuntimeException {

    public PieceNotFoundException(Square square) {
        super(String.format("Piece in square %s was not found", square));
    }
}
