package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.piece.Piece;

import java.util.List;

public class GameResponse {

    private final Long id;
    private final List<Piece> pieces;

    public GameResponse(Game game) {
        this.id = game.getId();
        this.pieces = game.getPieces();
    }

    public Long getId() {
        return id;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
