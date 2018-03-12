package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.entity.Move;
import com.chessmasters.chessapi.entity.piece.Piece;

import java.util.List;

public class GameResponse {

    private final Long id;
    private final List<Piece> pieces;
    private final List<Move> moves;

    public GameResponse(Game game) {
        this.id = game.getId();
        this.pieces = game.getPieces();
        this.moves = game.moves();
    }

    public Long getId() {
        return id;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Move> getMoves() {
        return moves;
    }
}
